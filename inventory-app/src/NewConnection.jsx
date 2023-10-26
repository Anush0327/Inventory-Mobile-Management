import { useState } from "react";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./NewConnection.scss";
import { Grid } from "@mui/material";

export default function NewConnection(props) {
  const [phoneNumber, setPhoneNumber] = useState("");
  const [customer, setCustomer] = useState("");
  const [provider, setProvider] = useState("");
  const [aadharUid, setAadharUid] = useState("");
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
  const changecustomerhandle = (event) => {
    setCustomer(event.target.value);
  };
  const changeaadharuidhandle = (event) => {
    setAadharUid(event.target.value);
  };
  const customerclick = async (event) => {
    event.preventDefault();
    fetchreservations();
  };
  const onreserve = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/api/number/reserve", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          aadharUID: aadharUid,
          customerName: customer,
          reservingNumber: phoneNumber,
          provider: provider,
          connectionType: props.connection.type,
          numberOfDaysToReserve: 90,
        })
      });
      if (response.ok) {
        const data = await response.json()

        if (data) {
          toast.success("reserved Sim successfully", {
            position: "top-center",
            autoClose: 5000,
          });
        }
        else {
          toast.error("reservation failed", {
            position: "top-center",
            autoClose: 5000,
          })
        }
        setTimeout(() => {
          window.location.reload();
        }, 5000);
      }
      else {
        console.error("failed to fetch data");
      }
    } catch (e) {
      console.error("Error:", e);
    }
  };
  // eslint-disable-next-line react-hooks/exhaustive-deps
  const fetchreservations = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/number/reservations?aadharUID=${aadharUid}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        }
      });
      if (response.ok) {
        const data = await response.json();
        setReservation(data);
        console.log(data);
        if (data === true) {
          toast.success("all Reservations fetched Successfully", {
            position: "top-center",
            autoClose: 5000, // Auto close the notification after 5 seconds
          });
        }
      }
      else {
        console.error("failed to fetch data");
      }
    } catch (e) {
      console.error("Error:", e);
    }
  };
  const onsubmit = async (event) => {
    event.preventDefault();
    console.log(
      JSON.stringify({
        aadharUID: aadharUid,
        customerNumber: customer,
        phoneNumber: phoneNumber,
        provider: provider,
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
          aadharUID: aadharUid,
          customerName: customer,
          aadharUid: aadharUid,
          reservingNumber: phoneNumber,
          provider: provider,
          connectionType: props.connection.type,
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
    <Grid container spacing={2}>
      <Grid xs={16}>
        <div className="prepaid-container">
          <form method="POST" className="login-form" onSubmit={onsubmit}>
            <h3>{props.connection.type} Reservation/NewConnection</h3>
            <hr />
            <label htmlFor="UID" className="form-label">
              Aadhar UID
            </label>
            <input
              type="text"
              value={aadharUid}
              name="UID"
              onChange={changeaadharuidhandle}
              className="form-input"
              required
            />
            <label htmlFor="customer" className="form-label">
              Customer
            </label>
            <input
              type="text"
              value={customer}
              name="customer"
              onChange={changecustomerhandle}
              className="form-input"
              required
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
              required
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
                required
              />
              Airtel
            </label>
            <label className="radio-label">
              <input
                type="radio"
                name="provider"
                value="JIO"
                onChange={changeproviderhandle}
                required
              />
              Jio
            </label>
            <label className="radio-label">
              <input
                type="radio"
                name="provider"
                value="VI"
                onChange={changeproviderhandle}
                required
              />
              Vodafone Idea
            </label>
            <label className="radio-label">
              <input
                type="radio"
                name="provider"
                value="AIRCEL"
                onChange={changeproviderhandle}
                required
              />
              Aircel
            </label>
            <button type="submit" className="button">
              Buy
            </button>
            <button type="button" onClick={onreserve} className="button">
              Reserve
            </button>
          </form>
        </div>
        </Grid>
    </Grid>
  );
}