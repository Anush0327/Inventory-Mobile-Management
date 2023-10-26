package com.example.inventorynumbermanagementapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.inventorynumbermanagementapi.business.RandomICCID;
import com.example.inventorynumbermanagementapi.dto.ReservationDTO;
import com.example.inventorynumbermanagementapi.dto.SimDTO;
import com.example.inventorynumbermanagementapi.entity.Customer;
import com.example.inventorynumbermanagementapi.entity.ICCID;
import com.example.inventorynumbermanagementapi.entity.IMEI;
import com.example.inventorynumbermanagementapi.entity.MSISDN;
import com.example.inventorynumbermanagementapi.entity.Reservation;
import com.example.inventorynumbermanagementapi.entity.SIM;
import com.example.inventorynumbermanagementapi.repository.CustomerRepository;
import com.example.inventorynumbermanagementapi.repository.ICCIDRepository;
import com.example.inventorynumbermanagementapi.repository.IMEIRepository;
import com.example.inventorynumbermanagementapi.repository.MSISDNRepository;
import com.example.inventorynumbermanagementapi.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private IMEIRepository imeiRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private static final int DEFAULT_NUMBER_OF_DAYS_TO_RESERVE = 90;

    // writing a method to store the reservation
    public boolean reserveTheNumber(ReservationDTO reservationDTO) {
        Customer customer = getOrCreateCustomer(reservationDTO.getAadharUID(), reservationDTO.getCustomerName());
        List<Reservation> reservations = reservationRepository.findAll();
        Optional<Reservation> exising = reservations.stream()
                .filter(reservation -> reservation.getPhoneNumber().equals(reservationDTO.getReservingNumber()))
                .findFirst();
        if (exising.isPresent()) {
            return false;
        }
        Reservation newReservation = new Reservation();
        newReservation.setConnectionType(reservationDTO.getConnectionType());
        newReservation.setCustomer(customer);
        newReservation.setPhoneNumber(reservationDTO.getReservingNumber());
        newReservation.setProvider(reservationDTO.getProvider());
        newReservation.setReservationDateTime(LocalDateTime.now());
        reservationRepository.save(newReservation);
        return true;
    }

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void removeAllExpiredReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationDateTime().plusDays(DEFAULT_NUMBER_OF_DAYS_TO_RESERVE)
                    .isBefore(LocalDateTime.now())) {
                reservationRepository.delete(reservation);
            }
        }
    }

    public List<Reservation> getAllReservationsOf(String aadharUID) {
        Customer customer = customerRepository.findByAadharUID(aadharUID).orElse(null);
        if (customer == null) {
            return Collections.emptyList();
        } else {
            return reservationRepository.findAllByCustomerId(customer.getId());
        }
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    private Boolean alreadyReservedNumber(String number, String customerName, String aadharUid) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> customerReservations = reservationRepository
                .findAllByCustomerId(getOrCreateCustomer(aadharUid, customerName).getId());
        for (Reservation reservation : customerReservations) {
            if (reservation.getPhoneNumber().equals(number))
                return false;
        }
        for (Reservation reservation : reservations) {
            if (reservation.getPhoneNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    public boolean issueInitiation(ReservationDTO reservationDTO) {
        Customer customer = getOrCreateCustomer(reservationDTO.getAadharUID(), reservationDTO.getCustomerName());
        MSISDN existingMsisdn = msisdnRepository.findByMsisdnNumber(reservationDTO.getReservingNumber()).orElse(null);

        if (existingMsisdn != null || alreadyReservedNumber(reservationDTO.getReservingNumber(),
                reservationDTO.getCustomerName(), reservationDTO.getAadharUID())) {
            return false; // Return false if MSISDN already exists.
        }

        SIM sim = createReservation(reservationDTO, customer);
        List<Reservation> reservations = reservationRepository.findAllByCustomerId(customer.getId());
        Optional<Reservation> optReservation = reservations.stream()
                .filter(reservation -> reservation.getPhoneNumber().equals(reservationDTO.getReservingNumber()))
                .findFirst();
        if (optReservation.isPresent()) {
            reservationRepository.delete(optReservation.get());
        }
        return sim != null;
    }

    private Customer getOrCreateCustomer(String aadharUid, String customerName) {
        return customerRepository.findByAadharUID(aadharUid).orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setAadharUID(aadharUid);
            newCustomer.setName(customerName);
            customerRepository.save(newCustomer);
            return newCustomer;
        });
    }

    private SIM createReservation(ReservationDTO reservationDTO, Customer customer) {
        ICCID newIccid = new ICCID();
        newIccid.setIccidNumber(RandomICCID.generate(reservationDTO.getProvider()));
        newIccid.setProvider(reservationDTO.getProvider());
        System.out.println(newIccid.getIccidNumber());
        iccidRepository.save(newIccid);

        MSISDN newMsisdn = new MSISDN();
        newMsisdn.setMsisdnNumber(reservationDTO.getReservingNumber());
        msisdnRepository.save(newMsisdn);

        SIM newSim = new SIM();
        newSim.setIccid(newIccid);
        newSim.setMsisdn(newMsisdn);
        newSim.setIssuedDateTime(LocalDateTime.now());
        if (customer.getSims() == null) {
            customer.setSims(new ArrayList<>());
        }
        newSim.setSimType(reservationDTO.getConnectionType());
        customer.getSims().add(newSim);
        customerRepository.save(customer);
        return newSim;
    }

    public Boolean activate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<SIM> simsToActivate = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getSims() != null) {
                for (SIM sim : customer.getSims()) {
                    if (!sim.isActivated()) {
                        LocalDateTime issuedDateTime = sim.getIssuedDateTime();
                        if (issuedDateTime.plusMinutes(1).isBefore(currentDateTime)) {
                            simsToActivate.add(sim);
                        }
                    }
                }
            }
        }

        for (SIM sim : simsToActivate) {
            activateReservation(sim);
        }
        return !simsToActivate.isEmpty();
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void autoActivate() {
        if (activate()) {
            System.out.println("Activated some SIM cards.");
        }
    }

    private void activateReservation(SIM sim) {
        if (sim == null || sim.getIccid() == null || sim.getMsisdn() == null)
            return;
        sim.setActivated(true);
        iccidRepository.save(sim.getIccid());
        msisdnRepository.save(sim.getMsisdn());
        System.out.println(sim.isActivated());
    }

    public boolean imeiAlreadyExist(String imei) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            for (SIM sim : customer.getSims()) {
                if (sim.getImei() == null) {
                    return false;
                }
                if (sim.getImei().getImeiNumber().equals(imei))
                    return true;
            }
        }
        return false;
    }

    private boolean setFlagForIMEI(List<SIM> sims, String phoneNumber, String imeiNumber) {

        var simToActivate = sims.stream().filter(sim -> sim.getMsisdn().getMsisdnNumber().equals(phoneNumber))
                .findFirst();
        if (simToActivate.isEmpty()) {
            return false;
        }
        if (imeiAlreadyExist(imeiNumber))
            return false;
        IMEI newImei = new IMEI();
        newImei.setImeiNumber(imeiNumber);
        simToActivate.get().setImei(newImei);
        imeiRepository.save(newImei);
        return true;
    }

    @Transactional
    public Boolean setSIMInPhoneWithIMEI(String imeiNumber, String phoneNumber) {
        List<Customer> customers = customerRepository.findAll();
        Boolean flag = false;
        customers.stream().forEach(customer -> setFlagForIMEI(customer.getSims(), phoneNumber, imeiNumber));
        var changedCustomer = customers.stream()
                .filter(customer -> setFlagForIMEI(customer.getSims(), phoneNumber, imeiNumber)).findFirst();
        if (changedCustomer.isPresent()) {
            flag = true;
        }
        // for (Customer customer : customers) {
        // for (SIM sim : customer.getSims()) {
        // if(imeiAlreadyExist(imeiNumber)){
        // return false;
        // }
        // if (sim.getMsisdn().getMsisdnNumber().equals(phoneNumber)) {
        // IMEI newImei = new IMEI();
        // newImei.setImeiNumber(imeiNumber);
        // sim.setImei(newImei);
        // imeiRepository.save(newImei);
        // flag = true;
        // }
        // }
        // customerRepository.save(customer);
        // }
        return flag;
    }

    public List<SIM> getAllPrepaidSims() {
        List<SIM> allSims = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            for (SIM sim : customer.getSims()) {
                if (sim.getSimType().equals("prepaid"))
                    allSims.add(sim);
            }
        }
        return allSims;
    }

    public Boolean changeProvider(ReservationDTO reservationDTO) {
        Customer customer = getOrCreateCustomer(reservationDTO.getAadharUID(), reservationDTO.getCustomerName());

        Boolean status = changeProviderTo(reservationDTO.getReservingNumber(), reservationDTO.getProvider(), customer,
                reservationDTO.getConnectionType());
        return status;
    }

    public Boolean changeProviderTo(String msisdn, String provider, Customer customer, String connectionType) {
        Boolean flag = false;
        for (SIM sim : customer.getSims()) {
            if (sim.getMsisdn().getMsisdnNumber().equals(msisdn)) {
                if (sim.getIccid().getProvider().equals(provider))
                    return flag;
                else {
                    ICCID newIccid = new ICCID();
                    newIccid.setIccidNumber(RandomICCID.generate(provider));
                    newIccid.setProvider(provider);
                    sim.setIccid(newIccid);
                    sim.setSimType(connectionType);
                    sim.setActivated(false);
                    sim.setIssuedDateTime(LocalDateTime.now());
                    iccidRepository.save(newIccid);
                    flag = true;
                }
            }
        }
        customerRepository.save(customer);
        return flag;
    }

    public List<SIM> getAllPostpaidSims() {
        List<SIM> allSims = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            for (SIM sim : customer.getSims()) {
                if (sim.getSimType().equals("postpaid"))
                    allSims.add(sim);
            }
        }
        return allSims;
    }

    public List<SimDTO> convertToDTO(List<SIM> sims) {
        List<SimDTO> simDTOs = new ArrayList<>();
        for (SIM sim : sims) {
            SimDTO simDTO = new SimDTO();
            simDTO.setId(sim.getId());
            simDTO.setIccid(sim.getIccid().getIccidNumber());
            simDTO.setMsisdn(sim.getMsisdn().getMsisdnNumber());
            simDTO.setImei(sim.getImei() == null ? "" : sim.getImei().getImeiNumber());
            simDTO.setActivated(sim.isActivated());
            simDTO.setIssuedDateTime(sim.getIssuedDateTime().toString());
            simDTO.setConnectionType(sim.getSimType());
            simDTOs.add(simDTO);
        }
        return simDTOs;
    }

    public Boolean replaceSim(ReservationDTO reservationDTO) {
        System.out.println(reservationDTO);
        Boolean flag = false;
        Customer customer = getOrCreateCustomer(reservationDTO.getAadharUID(), reservationDTO.getCustomerName());
        for (SIM sim : customer.getSims()) {
            if (sim.getIccid() == null || sim.getMsisdn() == null) {
                System.out.println(sim);
                return false;
            } else if (sim.getMsisdn().getMsisdnNumber().equals(reservationDTO.getReservingNumber())
                    && sim.getIccid().getProvider().equals(reservationDTO.getProvider())
                    && sim.getSimType().equals(reservationDTO.getConnectionType())) {
                String newIccid = RandomICCID.generate(reservationDTO.getProvider());
                sim.getIccid().setIccidNumber(newIccid);
                flag = true;
            } else {
            }
        }
        customerRepository.save(customer);
        return flag;
    }

    public List<SIM> getInactiveSims() {
        List<Customer> customers = customerRepository.findAll();
        List<SIM> inactivesims = new ArrayList<>();
        for (Customer customer : customers) {
            for (SIM sim : customer.getSims()) {
                if (!sim.isActivated()) {
                    inactivesims.add(sim);
                }
            }
        }
        return inactivesims;
    }
}
