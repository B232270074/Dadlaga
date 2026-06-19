import logo from "../assets/helpy-logo.png";

export default function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-left">
        <div className="navbar-logo">
          <img src={logo} alt="Helpym Logo" className="logo-image" />
        </div>
      </div>
      <div className="navbar-right">
        <div className="navbar-help">
          <span className="help-icon">❓</span>
          Help
        </div>
        <div className="navbar-status">
          <span className="dot"></span>
          Connected
        </div>
      </div>
    </nav>
  );
}