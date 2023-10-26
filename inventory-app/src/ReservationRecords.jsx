import { useEffect, useState } from "react";
import './ReservationRecords.scss'; // Import the CSS file
import Airtel from "./Airtel";
import Jio from "./Jio";
import VI from "./VI";

export default function ReservationRecords() {
    const [sims, setSims] = useState([]);

    const formatDateTime = (isoDateTime) => {
        const date = new Date(isoDateTime);
        return date.toLocaleString(); // You can customize the format here
    };
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
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>MSISDN Number</th>
                        <th>provider</th>
                        <th>Reservation Date</th>
                        <th>ConnectionType</th>
                    </tr>
                </thead>
                <tbody>
                    {sims.map((sim) => (
                        <tr key={sim.id}>
                            <td>{sim.id}</td>
                            <td>{sim.phoneNumber}</td>
                            <td>{sim.provider==="AIRTEL"? <Airtel/> :sim.provider==="JIO"?<Jio/>:<VI/>}</td>
                            <td>{formatDateTime(sim.reservationDateTime)}</td>
                            <td>{sim.connectionType}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
