import { useState } from "react";

export default function Home(){
    const [username,setUsername] = useState('');
    const [password,setPassword] = useState('');

    const onsubmit = (event) => {
        event.preventDefault();
        const response = fetch("http://localhost:8080/api/auth/token", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username : {username},
        password : {password},
      })
    })
    const body = response.json();
    console.log(body);
    }
    const handleUserChange = (event) => {
        setUsername(event.target.value);
    }
    const handlePassChange = (event) => {
        setPassword(event.target.value);
    }
    return(
        <div>
            <form className="login-form">
                <label htmlFor="username">username</label>
                <input type="text" value={username} name="username" onChange={handleUserChange}/>
                <label htmlFor="password">password</label>
                <input type="text" value={password} name="password" onChange={handlePassChange}/>
                <input type="submit" onSubmit={onsubmit} />
            </form>
        </div>
        );

};