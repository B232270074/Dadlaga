import { useEffect, useState } from "react";
import SummaryCards from "../components/SummaryCards";
import OperatorTable from "../components/OperatorTable";

export default function Dashboard() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("/api/kpis/employees")
      .then(res => res.json())
      .then(res => {
        setData(res);
        setLoading(false);
      })
      .catch(err => {
        console.error("API error:", err);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div className="loading-text">Loading KPI data...</div>;
  }

  useEffect(() => {
  fetch("/api/kpis/employees")
    .then(res => res.json())
    .then(res => {
      console.log("BACKEND DATA:", res);
      setData(res);
    });
}, []);
  return (
    <div className="dashboard">
      <SummaryCards data={data} />

      <div className="table-section">
        <div className="table-header">
          <h2>Operator KPI Table</h2>
        </div>

        <OperatorTable operators={data} />
      </div>
    </div>
  );
}