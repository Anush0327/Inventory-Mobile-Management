import { useState } from "react";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./NewConnection.css";

export default function NewConnection(props) {
    const [phoneNumber, setPhoneNumber] = useState("");
    const [location, setLocation] = useState("");
    const [customer, setCustomer] = useState("");
    const [provider, setProvider] = useState("");
    const [reservation, setReservation] = useState([]);
    const changereservedphonenumber = (event) => {
      setPhoneNumber(event.target.value);
    }
    const changephonenumberhandle = (event) => {
      setPhoneNumber(event.target.value);
    };
    const changeproviderhandle = (event) => {
      setProvider(event.target.value);
    };
    const changelocationhandle = (event) => {
      setLocation(event.target.value);
    };
    const changecustomerhandle = (event) => {
      setCustomer(event.target.value);
    };
    const customerclick = async (event) => {
      event.preventDefault();
      fetchreservations();
    };
    const onreserve = async (event) => {
      event.preventDefault();
      try{
        const response = await fetch("http://localhost:8080/api/number/reserve",{
          method : "POST",
          headers : {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            customerName: customer,
            reservingNumber: phoneNumber,
            provider: provider,
            location: location,
            connectionType:props.connection.type,
            numberOfDaysToReserve:90,
          })
        });
        if(response.ok){
          const data = await response.json()

          if(data){
            toast.success("reserved Sim successfully",{
              position : "top-center",
              autoClose : 5000,
            });
          }
          else{
            toast.error("reservation failed",{
              position : "top-center",
              autoClose : 5000,
            })
          }
          setTimeout(() => {
            window.location.reload();
          }, 5000);
        }
        else{
          console.error("failed to fetch data");
        }
      }catch(e){
        console.error("Error:",e);
      }
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
    const fetchreservations = async () => {
      try{
        const response = await fetch(`http://localhost:8080/api/number/reservations?customerName=${customer}`,{
          method : "GET",
          headers : {
            "Content-Type": "application/json",
          }
      });
      if(response.ok){
        const data = await response.json();
        setReservation(data);
        console.log(data);
        if(data === true){
        toast.success("all Reservations fetched Successfully", {
          position: "top-center",
          autoClose: 5000, // Auto close the notification after 5 seconds
        });
      }
    }
    else{
      console.error("failed to fetch data");
    }
  }catch(e){
    console.error("Error:",e);
  }
};
    const onsubmit = async (event) => {
      event.preventDefault();
      console.log(
        JSON.stringify({
          customerNumber: customer,
          phoneNumber: phoneNumber,
          provider: provider,
          location: location,
          connectionType: props.connection.type,
        })
      );
      try {
        const response = await fetch("http://localhost:8080/api/number/newconnection", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            customerName: customer,
            reservingNumber: phoneNumber,
            provider: provider,
            location: location,
            connectionType:props.connection.type,
          }),
        });
        if (response.ok) {
          toast.success("Sim has been registered successfully", {
            position: "top-center",
            autoClose: 5000, // Auto close the notification after 5 seconds
          });
          // Reload the page after a short delay (5 seconds)
          setTimeout(() => {
            window.location.reload();
          }, 5000);
          console.log(await response.json());
        } else {
          console.error("Failed to fetch data.");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    };
    return (
      <div className="prepaid-container">
        <form method="POST" className="login-form" onSubmit={onsubmit}>
        <h3>{props.connection.type} Sim reservation</h3>
        <hr/>
          <label htmlFor="customer" className="form-label">
            Customer
          </label>
          <input
            type="text"
            value={customer}
            name="customer"
            onChange={changecustomerhandle}
            className="form-input"
          />
          <button onClick={customerclick} className="button">Check</button>
          <label htmlFor="phone-number" className="form-label">
            Phone Number
          </label>
          <input
            type="text"
            value={phoneNumber}
            name="phone-number"
            onChange={changephonenumberhandle}
            className="form-input"
          />
          <select onChange={changereservedphonenumber} onClick={changereservedphonenumber} value={phoneNumber}>
            {reservation.map((reser) => (
            <option key={reser.id} >{reser.phoneNumber}</option>))}
          </select>
          <label htmlFor="provider" className="form-label radio-label">
            Networks
          </label>
          <label className="radio-label">
            <input
              type="radio"
              name="provider"
              value="AIRTEL"
              onChange={changeproviderhandle}
            />
            Airtel
          </label>
          <label className="radio-label">
            <input
              type="radio"
              name="provider"
              value="JIO"
              onChange={changeproviderhandle}
            />
            Jio
          </label>
          <label className="radio-label">
            <input
              type="radio"
              name="provider"
              value="VI"
              onChange={changeproviderhandle}
            />
            Vodafone Idea
          </label>
          <label className="radio-label">
            <input
              type="radio"
              name="provider"
              value="AIRCEL"
              onChange={changeproviderhandle}
            />
            Aircel
          </label>
          <label htmlFor="location" className="form-label">
            Location
          </label>
          <input
            type="text"
            value={location}
            name="location"
            onChange={changelocationhandle}
            className="form-input"
          />
          <button type="submit" className="button">
            Buy
          </button>
          <button type="button" onClick={onreserve} className="button">
            Reserve
          </button>
        </form>
      </div>
    );
  }