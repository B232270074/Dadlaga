import React, { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import axios from "axios";
import Navbar from "./components/Navbar.jsx";
import SummaryCards from "./components/SummaryCards.jsx";
import KpiTable from "./components/KpiTable.jsx";
import Login from "./components/Login.jsx";

// Axios interceptor - token автоматаар илгээх (Логин хүсэлтэд бүү нэм)
axios.interceptors.request.use(
  (config) => {
    // Login хүсэлтэд token бүү нэм
    if (config.url.includes("/api/auth/login")) {
      return config;
    }
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

function Dashboard({ data, loading, error }) {
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

export default function App() {
  const [isAuth, setIsAuth] = useState(false);
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsAuth(true);
    }
  }, []);

  useEffect(() => {
    if (!isAuth) return;

    axios
      .get("http://localhost:8080/api/kpis/employees")
      .then((response) => {
        setData(response.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("API error:", err);
        if (err.response?.status === 401) {
          localStorage.removeItem("token");
          setIsAuth(false);
          setError("Session дууссан. Дахин нэвтрэнэ үү.");
        } else {
          setError("Өгөгдөл татахад алдаа гарлаа.");
        }
        setLoading(false);
      });
  }, [isAuth]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login setAuth={setIsAuth} />} />
        <Route
          path="/dashboard"
          element={
            isAuth ? (
              <Dashboard data={data} loading={loading} error={error} />
            ) : (
              <Navigate to="/login" replace />
            )
          }
        />
        <Route path="*" element={<Navigate to={isAuth ? "/dashboard" : "/login"} replace />} />
      </Routes>
    </BrowserRouter>
  );
}
