export default function OperatorTable({ operators = [] }) {
  return (
    <table className="operator-table">
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Department</th>
          <th>KPI</th>
        </tr>
      </thead>

      <tbody>
        {operators.map((op, index) => (
          <tr key={op.employeeId || index}>
            <td>{index + 1}</td>

            {/* 🔥 FIX HERE */}
            <td>{op.employeeName || op.name || "N/A"}</td>
            <td>{op.department || op.dept || "N/A"}</td>
            <td>{op.kpiScore ?? op.kpi ?? 0}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}