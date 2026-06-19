package com.kpi.kpi_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeKpiDto {

    private Long employeeId;
    private String employeeName;

    // === Incoming (COME) ===
    private long totalIncomingCalls;       // Нийт дуудлага (incoming)
    private long answeredCalls;            // Авсан дуудлага
    private long otherCalls;               // Бусад (OUT status among incoming context)
    private long missedCalls;              // Алдсан дуудлага (UNSUCCESSFUL)
    private long incomingTalkTimeSec;      // Ярьсан хугацаа (incoming, seconds)
    private double incomingAvgTalkTimeSec; // Ярьсан дундаж хугацаа
    private long incomingServiceTimeSec;   // Үйлчилсэн хугацаа
    private double incomingAvgServiceTimeSec; // Үйлчилсэн дундаж хугацаа
    private double missedCallRate;         // Дуудлага алдалтын түвшин (%)
    private double avgAnswerSpeedSec;      // Дуудлага авалтын дундаж хурд (seconds)

    // === Outgoing (OUT) ===
    private long outgoingCalls;            // Гарсан дуудлага
    private long outgoingTalkTimeSec;      // Ярьсан хугацаа
    private double outgoingAvgTalkTimeSec; // Ярьсан дундаж хугацаа
    private long outgoingServiceTimeSec;   // Үйлчилсэн хугацаа
    private double outgoingAvgServiceTimeSec; // Үйлчилсэн дундаж хугацаа

    // === Registration status ===
    private long regStatusSuccess;         // call_reg_status = 1 count
    private long regStatusFail;            // call_reg_status = 0 count
    private double regSuccessRate;         // percentage

    // === Attendance ===
    private double avgCheckInHour;
    private double avgCheckOutHour;
    private double avgWorkHours;           // checkout - checkin

    // === KPI Score (0-100) ===
    private double kpiScore;
}
