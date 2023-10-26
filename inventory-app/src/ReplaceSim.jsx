import { useState } from "react";
import { toast } from "react-toastify";
import "./ReplaceSim.scss"
export default function ReplaceSim(props){
    const [phoneNumber, setPhoneNumber] = useState("");
    const [customer, setCustomer] = useState("");
    const [provider, setProvider] = useState("");
    const changephonenumberhandle = (event) => {
      setPhoneNumber(event.target.value);
    };
    const changeproviderhandle = (event) => {
      setProvider(event.target.value);
    };
    const changecustomerhandle = (event) => {
      setCustomer(event.target.value);
    };
    const onsubmit = async (event) => {
      event.preventDefault();
      console.log(
        JSON.stringify({
          customerNumber: customer,
          phoneNumber: phoneNumber,
          provider: provider,
          connectionType: props.connection.type,
        })
      );
      try {
        const response = await fetch("http://localhost:8080/api/number/replacesim", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            customerName: customer,
            reservingNumber: phoneNumber,
            provider: provider,
            connectionType:props.connection.type,
          }),
        });
        if (response.ok) {
            const data = await response.json();
            console.log(data);
        if(data === true){
          toast.success("Sim replace request is processed successfully", {
            position: "top-center",
            autoClose: 5000, // Auto close the notification after 5 seconds
          });
        }
        else{
            toast.error("Sim replace request is not processed", {
                position: "top-center",
                autoClose: 5000, // Auto close the notification after 5 seconds
              });
        }
          // Reload the page after a short delay (5 seconds)
          setTimeout(() => {
            window.location.reload();
          }, 5000);
        } else {
          console.error("Failed to fetch data.");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    };
    return (
      <div className="replace-container">
        <form method="POST" className="replace-form" onSubmit={onsubmit}>
        <h3>Sim Replace</h3>
        <p>Enter your previous valid details to get a new sim.</p>
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
            required
          />
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
          <button type="submit" className="button">
            Submit
          </button>
        </form>
      </div>
    );
}