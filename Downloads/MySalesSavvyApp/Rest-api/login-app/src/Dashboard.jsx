import React from "react";
import "./dashboard.css";

function Dashboard({ username }) {
  return (
    <div className="dashboard">
      <div className="card">
        <h1>👋 Hello {username}</h1>
        <p>Welcome back! You have successfully logged in.</p>

        <div className="stats">
          <div className="box">
            <h3>📦 Items</h3>
            <p>12</p>
          </div>

          <div className="box">
            <h3>💰 Revenue</h3>
            <p>₹45,000</p>
          </div>

          <div className="box">
            <h3>⚡ Status</h3>
            <p>Active</p>
          </div>
        </div>

        <button
          onClick={() => window.location.reload()}
          className="logout"
        >
          Logout
        </button>
      </div>
    </div>
  );
}

export default Dashboard;