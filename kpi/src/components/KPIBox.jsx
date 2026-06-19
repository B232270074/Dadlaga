export default function KPIBox({ title, value }) {
  return (
    <div className="summary-card">
      <div className="card-label">{title}</div>
      <div className="card-value">{value}</div>
    </div>
  );
}