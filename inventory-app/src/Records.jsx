import { useEffect, useState } from "react";
import './Records.scss'; // Import the CSS file

export default function Records(props) {
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
    
        return sims.filter((sim) => sim.msisdn.toLowerCase().includes(sanitizedSearchText));
    };
    const filteredSims = filterSimsByMSISDN(sims, searchText);
    // Function to fetch SIMs from the API
    // eslint-disable-next-line react-hooks/exhaustive-deps
    // const fetchSims = async () => {
    //     try {
    //         const response = await fetch(`http://localhost:8080/api/number/all${props.connection.type}sims`);
    //         if (!response.ok) {
    //             throw new Error('Failed to fetch data');
    //         }
    //         const simsData = await response.json();
    //         setSims(simsData);
    //     } catch (error) {
    //         console.error('Error fetching SIMs:', error);
    //     }
    // };

    // Use the useEffect hook to fetch SIMs when the component mounts
    useEffect(() => {
        const fetchSims = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/number/all${props.connection.type}sims`);
                if (!response.ok) {
                    throw new Error('Failed to fetch data');
                }
                const simsData = await response.json();
                setSims(simsData);
            } catch (error) {
                console.error('Error fetching SIMs:', error);
            }
        };
        fetchSims();
    }, [props.connection.type]);

    return (
        <div className="record-table">
            <h3>{props.connection.type} Sims</h3>
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
                        <th>ID</th>
                        <th>MSISDN Number</th>
                        <th>ICCID</th>
                        <th>IMEI Number</th>
                        <th>Reservation Date</th>
                        <th>Activity Status</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredSims.map((sim) => (
                        <tr key={sim.id}>
                            <td>{sim.id}</td>
                            <td>{sim.msisdn}</td>
                            <td>{sim.iccid}</td>
                            <td>{sim.imei ?? "N/A"}</td>
                            <td>{formatDateTime(sim.issuedDateTime)}</td>
                            <td>
                                <button className={sim.activated ? "active" : "inactive"}>
                                    {sim.activated ? "Active" : "Inactive"}
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
