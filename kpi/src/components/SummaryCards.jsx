export default function SummaryCards({ data = [] }) {
  const totalCalls = data.reduce((a, b) => a + (b.totalIncomingCalls || 0), 0);
  const answered = data.reduce((a, b) => a + (b.answeredCalls || 0), 0);
  const missed = data.reduce((a, b) => a + (b.missedCalls || 0), 0);
  const avgKpi =
    data.length > 0
      ? (data.reduce((a, b) => a + (b.kpiScore || 0), 0) / data.length).toFixed(1)
      : 0;

  return (
    <div className="summary-grid">
      <div className="summary-card">
        <div className="card-label">Total Calls</div>
        <div className="card-value">{totalCalls}</div>
      </div>

      <div className="summary-card">
        <div className="card-label">Answered</div>
        <div className="card-value">{answered}</div>
      </div>

      <div className="summary-card">
        <div className="card-label">Missed</div>
        <div className="card-value">{missed}</div>
      </div>

      <div className="summary-card">
        <div className="card-label">Avg KPI</div>
        <div className="card-value">{avgKpi}</div>
      </div>
    </div>
  );
}