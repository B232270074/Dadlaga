import React, { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "./components/Navbar.jsx";
import SummaryCards from "./components/SummaryCards.jsx";
import KpiTable from "./components/KpiTable.jsx";
import {MOCK_DATA} from "./MockData.jsx";
export default function App() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/kpis/employees")
      .then((response) => {
        setData(response.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("API error:", err);
        setError("Өгөгдөл татахад алдаа гарлаа.");
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <>
        <Navbar />
        <div className="loading-container">
          <div className="loading-spinner"></div>
          <div className="loading-text">Өгөгдөл татаж байна...</div>
        </div>
      </>
    );
  }

  if (error) {
    return (
      <>
        <Navbar />
        <div className="error-container">
          <div className="error-icon">⚠️</div>
          <div className="error-message">{error}</div>
          <button className="retry-btn" onClick={() => window.location.reload()}>
            Дахин оролдох
          </button>
        </div>
      </>
    );
  }

  return (
    <>
      <Navbar />
      <div className="dashboard">
        <SummaryCards data={data} />
        <div className="table-section">
          <KpiTable data={data} />
        </div>
      </div>
    </>
  );
}