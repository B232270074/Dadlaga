import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import logo from "../assets/helpy-logo.png";

export default function Login({ setAuth }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", {
        username,
        password,
      });

      console.log("Login response:", response.data); // Лог харах

      if (response.data.token) {
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("user", JSON.stringify(response.data));
        setAuth(true);
        navigate("/dashboard");
      }
    } catch (err) {
      console.error("Login error:", err);
      setError(err.response?.data?.error || "Нэвтрэхэд алдаа гарлаа");
    } finally {
      setLoading(false);
    }
  };
  localStorage.removeItem("token");

  return (
    <div className="login-container">
      <div className="login-card">
        <div className="login-header">
          <img src={logo} alt="Helpym" className="login-logo" />
          <h1>Helpym</h1>
          <p>KPI Dashboard-д нэвтрэх</p>
        </div>

        <form onSubmit={handleSubmit} className="login-form">
          <div className="form-group">
            <label>Хэрэглэгчийн нэр</label>
            <div className="input-wrapper">
              <span className="input-icon">👤</span>
              <input
                type="text"
                placeholder="admin"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
              />
            </div>
          </div>

          <div className="form-group">
            <label>Нууц үг</label>
            <div className="input-wrapper">
              <span className="input-icon">🔒</span>
              <input
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
          </div>

          {error && <div className="login-error">{error}</div>}

          <button type="submit" className="login-btn" disabled={loading}>
            {loading ? "Нэвтрэж байна..." : "Нэвтрэх"}
          </button>
        </form>

        <div className="login-footer">
          <span>Туршилтын: admin / Password</span>
        </div>
      </div>
    </div>
  );
}