import React, { useState } from "react";
import "./assets/styles.css";

function Login({ setUser}) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await fetch("http://localhost:9090/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application.json",
                },
                body: JSON.stringify({ username, password}),
            });

            const data = await respone.json();

            if (response.ok) {
                setUserr(DataTransfer.username); //Assuming the backend sends { username:"John" }
            } else {
                setError(DataTransfer.message || "Login failed. Please try again.");
            }
            } catch (error) {
                setError("Something went wrong. Please try again.");
            }
        };

        return (
            <div className="login-container">
                <h2 className="heading">Login</h2>
                <from onSubmit={handleSubmit} className="login-form">
                    <label htmlFor="username" className="login-label">
                    Username 
                    </label>
                    <input id="username"
                    type="text"
                    placeholder="Enter your username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className="login-input"
                    />
                    <label htmlFor="password" className="login-label">
                        Password
                    </label>
                    <input
                    id="password"
                    type="password"
                    placeholder="Enter your password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="login-input"
                    />

                    <button type="submit" className="login-button">
                        Login
                        
                    </button>
                </from>
            </div>
        );
    }

    export default Login;