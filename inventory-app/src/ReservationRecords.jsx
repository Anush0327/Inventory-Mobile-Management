import { useEffect, useState } from "react";
import './ReservationRecords.scss'; // Import the CSS file
import Provider from "./Provider";

export default function ReservationRecords() {
    const [sims, setSims] = useState([]);
    const [searchText, setSearchText] = useState('');

    const formatDateTime = (isoDateTime) => {
        const date = new Date(isoDateTime);
        return date.toLocaleString(); // You can customize the format here
    };
    const filterSimsByMSISDN = (sims, searchText) => {
        const sanitizedSearchText = searchText.trim().toLowerCase();
        if (sanitizedSearchText === '') {
            return sims; // Return all SIMs if the search text is empty
        }
    
        return sims.filter((sim) => sim.phoneNumber.toLowerCase().includes(sanitizedSearchText));
    };
    const filteredSims = filterSimsByMSISDN(sims, searchText);
    // Function to fetch SIMs from the API
    const fetchSims = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/number/allreservations`);
            if (!response.ok) {
                throw new Error('Failed to fetch data');
            }
            const simsData = await response.json();
            console.log(simsData);
            setSims(simsData);
        } catch (error) {
            console.error('Error fetching SIMs:', error);
        }
    };

    // Use the useEffect hook to fetch SIMs when the component mounts
    useEffect(() => {
        fetchSims();
    }, []);

    return (
        <div className="record-table">
            <h3>Reserved Numbers</h3>
            <hr/>
            <input
            type="text"
            placeholder="Search by MSISDN"
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
            />
            <table>
                <thead>
                    <tr>
                        <th>MSISDN Number</th>
                        <th>provider</th>
                        <th>Reservation Date</th>
                        <th>ConnectionType</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredSims.map((sim) => (
                        <tr key={sim.id}>
                            <td>{sim.phoneNumber}</td>
                            <td>{<Provider provider={ {type:sim.provider}} />}</td>
                            <td>{formatDateTime(sim.reservationDateTime)}</td>
                            <td>{sim.connectionType}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
