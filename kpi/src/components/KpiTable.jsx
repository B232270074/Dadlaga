/** Format seconds into mm:ss or hh:mm:ss */
function fmtTime(sec) {
  if (!sec || sec <= 0) return "0:00";
  const s = Math.round(sec);
  const h = Math.floor(s / 3600);
  const m = Math.floor((s % 3600) / 60);
  const ss = s % 60;
  const pad = (n) => String(n).padStart(2, "0");
  return h > 0 ? `${h}:${pad(m)}:${pad(ss)}` : `${m}:${pad(ss)}`;
}

/** Format decimal hour (e.g. 9.03) to "09:02" */
function fmtHour(decimalHour) {
  if (!decimalHour) return "—";
  const h = Math.floor(decimalHour);
  const m = Math.round((decimalHour - h) * 60);
  return `${String(h).padStart(2, "0")}:${String(m).padStart(2, "0")}`;
}

function getKpiClass(score) {
  if (score >= 80) return "kpi-excellent";
  if (score >= 65) return "kpi-good";
  if (score >= 50) return "kpi-average";
  return "kpi-poor";
}

function getRateClass(rate, inverse = false) {
  if (inverse) {
    if (rate <= 10) return "rate-good";
    if (rate <= 25) return "rate-warning";
    return "rate-danger";
  }
  if (rate >= 70) return "rate-good";
  if (rate >= 50) return "rate-warning";
  return "rate-danger";
}

const AVATAR_INITIALS = {
  1: "БЭ", 2: "СГ", 3: "ТМ", 4: "ОЧ", 5: "ГБ",
  6: "НЭ", 7: "БМ", 8: "ЭЖ", 9: "МБ"
};

export default function KpiTable({ data }) {
  if (!data || data.length === 0) return null;

  return (
    <div className="table-wrapper">
      <table className="kpi-table">
        <thead>
          {/* Group headers */}
          <tr>
            <th rowSpan={2}>Операторын нэр</th>
            <th colSpan={10} className="group-header group-incoming">Хүлээн авсан дуудлага</th>
            <th colSpan={5} className="group-header group-outgoing">Гадагш залгасан дуудлага</th>
            <th colSpan={3} className="group-header group-reg">Бүртгэлийн төлөв</th>
            <th colSpan={3} className="group-header group-attendance">Ирцийн мэдээ</th>
            <th rowSpan={2} className="group-header group-score">KPI Оноо</th>
          </tr>
          {/* Sub headers */}
          <tr>
            {/* Incoming (10) */}
            <th>Нийт</th>
            <th>Авсан</th>
            <th>Бусад</th>
            <th>Алдсан</th>
            <th>Ярьсан хугацаа</th>
            <th>Дундаж ярьсан</th>
            <th>Үйлчилсэн</th>
            <th>Дундаж үйлчилсэн</th>
            <th>Алдалтын %</th>
            <th>Дундаж хурд (с)</th>
            {/* Outgoing (5) */}
            <th>Гарсан</th>
            <th>Ярьсан хугацаа</th>
            <th>Дундаж ярьсан</th>
            <th>Үйлчилсэн</th>
            <th>Дундаж үйлчилсэн</th>
            {/* Reg status (3) */}
            <th>Амжилттай</th>
            <th>Амжилтгүй</th>
            <th>Хувь %</th>
            {/* Attendance (3) */}
            <th>Ирсэн цаг</th>
            <th>Явсан цаг</th>
            <th>Ажилласан</th>
          </tr>
        </thead>
        <tbody>
          {data.map((emp) => (
            <tr key={emp.employeeId}>
              {/* Name */}
              <td>
                <div className="employee-cell">
                  <div className={`employee-avatar avatar-${emp.employeeId}`}>
                    {AVATAR_INITIALS[emp.employeeId] || emp.employeeName?.charAt(0)}
                  </div>
                  <div className="employee-info">
                    <div className="emp-name">{emp.employeeName}</div>
                    <div className="emp-id">ID: {emp.employeeId}</div>
                  </div>
                </div>
              </td>

              {/* Incoming */}
              <td>{emp.totalIncomingCalls}</td>
              <td style={{ color: '#34d399', fontWeight: 600 }}>{emp.answeredCalls}</td>
              <td>{emp.otherCalls}</td>
              <td className={getRateClass(emp.missedCalls > 2 ? 30 : 5, true)}
                  style={{ fontWeight: 600 }}>{emp.missedCalls}</td>
              <td className="time-value">{fmtTime(emp.incomingTalkTimeSec)}</td>
              <td className="time-value">{fmtTime(emp.incomingAvgTalkTimeSec)}</td>
              <td className="time-value">{fmtTime(emp.incomingServiceTimeSec)}</td>
              <td className="time-value">{fmtTime(emp.incomingAvgServiceTimeSec)}</td>
              <td className={getRateClass(emp.missedCallRate, true)}
                  style={{ fontWeight: 600 }}>{emp.missedCallRate}%</td>
              <td className="time-value">{emp.avgAnswerSpeedSec}с</td>

              {/* Outgoing */}
              <td>{emp.outgoingCalls}</td>
              <td className="time-value">{fmtTime(emp.outgoingTalkTimeSec)}</td>
              <td className="time-value">{fmtTime(emp.outgoingAvgTalkTimeSec)}</td>
              <td className="time-value">{fmtTime(emp.outgoingServiceTimeSec)}</td>
              <td className="time-value">{fmtTime(emp.outgoingAvgServiceTimeSec)}</td>

              {/* Reg status */}
              <td style={{ color: '#34d399' }}>{emp.regStatusSuccess}</td>
              <td style={{ color: '#f87171' }}>{emp.regStatusFail}</td>
              <td className={getRateClass(emp.regSuccessRate)}
                  style={{ fontWeight: 600 }}>{emp.regSuccessRate}%</td>

              {/* Attendance */}
              <td className="time-value">{fmtHour(emp.avgCheckInHour)}</td>
              <td className="time-value">{fmtHour(emp.avgCheckOutHour)}</td>
              <td className="time-value" style={{ fontWeight: 600 }}>
                {emp.avgWorkHours ? `${emp.avgWorkHours.toFixed(1)}ц` : '—'}
              </td>

              {/* KPI Score */}
              <td>
                <span className={`kpi-score-badge ${getKpiClass(emp.kpiScore)}`}>
                  {emp.kpiScore}
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
