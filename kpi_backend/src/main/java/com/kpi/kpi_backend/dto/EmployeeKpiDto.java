package com.kpi.kpi_backend.dto;

// Lombok-г устгасан
public class EmployeeKpiDto {

    private Long employeeId;
    private String employeeName;

    // Incoming
    private long totalIncomingCalls;
    private long answeredCalls;
    private long otherCalls;
    private long missedCalls;
    private long incomingTalkTimeSec;
    private double incomingAvgTalkTimeSec;
    private long incomingServiceTimeSec;
    private double incomingAvgServiceTimeSec;
    private double missedCallRate;
    private double avgAnswerSpeedSec;

    // Outgoing
    private long outgoingCalls;
    private long outgoingTalkTimeSec;
    private double outgoingAvgTalkTimeSec;
    private long outgoingServiceTimeSec;
    private double outgoingAvgServiceTimeSec;

    // Registration
    private long regStatusSuccess;
    private long regStatusFail;
    private double regSuccessRate;

    // Attendance
    private double avgCheckInHour;
    private double avgCheckOutHour;
    private double avgWorkHours;

    // KPI Score
    private double kpiScore;

    // Constructors
    public EmployeeKpiDto() {
    }

    public EmployeeKpiDto(Long employeeId, String employeeName, long totalIncomingCalls, long answeredCalls,
                         long otherCalls, long missedCalls, long incomingTalkTimeSec, double incomingAvgTalkTimeSec,
                         long incomingServiceTimeSec, double incomingAvgServiceTimeSec, double missedCallRate,
                         double avgAnswerSpeedSec, long outgoingCalls, long outgoingTalkTimeSec,
                         double outgoingAvgTalkTimeSec, long outgoingServiceTimeSec, double outgoingAvgServiceTimeSec,
                         long regStatusSuccess, long regStatusFail, double regSuccessRate, double avgCheckInHour,
                         double avgCheckOutHour, double avgWorkHours, double kpiScore) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.totalIncomingCalls = totalIncomingCalls;
        this.answeredCalls = answeredCalls;
        this.otherCalls = otherCalls;
        this.missedCalls = missedCalls;
        this.incomingTalkTimeSec = incomingTalkTimeSec;
        this.incomingAvgTalkTimeSec = incomingAvgTalkTimeSec;
        this.incomingServiceTimeSec = incomingServiceTimeSec;
        this.incomingAvgServiceTimeSec = incomingAvgServiceTimeSec;
        this.missedCallRate = missedCallRate;
        this.avgAnswerSpeedSec = avgAnswerSpeedSec;
        this.outgoingCalls = outgoingCalls;
        this.outgoingTalkTimeSec = outgoingTalkTimeSec;
        this.outgoingAvgTalkTimeSec = outgoingAvgTalkTimeSec;
        this.outgoingServiceTimeSec = outgoingServiceTimeSec;
        this.outgoingAvgServiceTimeSec = outgoingAvgServiceTimeSec;
        this.regStatusSuccess = regStatusSuccess;
        this.regStatusFail = regStatusFail;
        this.regSuccessRate = regSuccessRate;
        this.avgCheckInHour = avgCheckInHour;
        this.avgCheckOutHour = avgCheckOutHour;
        this.avgWorkHours = avgWorkHours;
        this.kpiScore = kpiScore;
    }

    // Getters and Setters
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public long getTotalIncomingCalls() { return totalIncomingCalls; }
    public void setTotalIncomingCalls(long totalIncomingCalls) { this.totalIncomingCalls = totalIncomingCalls; }

    public long getAnsweredCalls() { return answeredCalls; }
    public void setAnsweredCalls(long answeredCalls) { this.answeredCalls = answeredCalls; }

    public long getOtherCalls() { return otherCalls; }
    public void setOtherCalls(long otherCalls) { this.otherCalls = otherCalls; }

    public long getMissedCalls() { return missedCalls; }
    public void setMissedCalls(long missedCalls) { this.missedCalls = missedCalls; }

    public long getIncomingTalkTimeSec() { return incomingTalkTimeSec; }
    public void setIncomingTalkTimeSec(long incomingTalkTimeSec) { this.incomingTalkTimeSec = incomingTalkTimeSec; }

    public double getIncomingAvgTalkTimeSec() { return incomingAvgTalkTimeSec; }
    public void setIncomingAvgTalkTimeSec(double incomingAvgTalkTimeSec) { this.incomingAvgTalkTimeSec = incomingAvgTalkTimeSec; }

    public long getIncomingServiceTimeSec() { return incomingServiceTimeSec; }
    public void setIncomingServiceTimeSec(long incomingServiceTimeSec) { this.incomingServiceTimeSec = incomingServiceTimeSec; }

    public double getIncomingAvgServiceTimeSec() { return incomingAvgServiceTimeSec; }
    public void setIncomingAvgServiceTimeSec(double incomingAvgServiceTimeSec) { this.incomingAvgServiceTimeSec = incomingAvgServiceTimeSec; }

    public double getMissedCallRate() { return missedCallRate; }
    public void setMissedCallRate(double missedCallRate) { this.missedCallRate = missedCallRate; }

    public double getAvgAnswerSpeedSec() { return avgAnswerSpeedSec; }
    public void setAvgAnswerSpeedSec(double avgAnswerSpeedSec) { this.avgAnswerSpeedSec = avgAnswerSpeedSec; }

    public long getOutgoingCalls() { return outgoingCalls; }
    public void setOutgoingCalls(long outgoingCalls) { this.outgoingCalls = outgoingCalls; }

    public long getOutgoingTalkTimeSec() { return outgoingTalkTimeSec; }
    public void setOutgoingTalkTimeSec(long outgoingTalkTimeSec) { this.outgoingTalkTimeSec = outgoingTalkTimeSec; }

    public double getOutgoingAvgTalkTimeSec() { return outgoingAvgTalkTimeSec; }
    public void setOutgoingAvgTalkTimeSec(double outgoingAvgTalkTimeSec) { this.outgoingAvgTalkTimeSec = outgoingAvgTalkTimeSec; }

    public long getOutgoingServiceTimeSec() { return outgoingServiceTimeSec; }
    public void setOutgoingServiceTimeSec(long outgoingServiceTimeSec) { this.outgoingServiceTimeSec = outgoingServiceTimeSec; }

    public double getOutgoingAvgServiceTimeSec() { return outgoingAvgServiceTimeSec; }
    public void setOutgoingAvgServiceTimeSec(double outgoingAvgServiceTimeSec) { this.outgoingAvgServiceTimeSec = outgoingAvgServiceTimeSec; }

    public long getRegStatusSuccess() { return regStatusSuccess; }
    public void setRegStatusSuccess(long regStatusSuccess) { this.regStatusSuccess = regStatusSuccess; }

    public long getRegStatusFail() { return regStatusFail; }
    public void setRegStatusFail(long regStatusFail) { this.regStatusFail = regStatusFail; }

    public double getRegSuccessRate() { return regSuccessRate; }
    public void setRegSuccessRate(double regSuccessRate) { this.regSuccessRate = regSuccessRate; }

    public double getAvgCheckInHour() { return avgCheckInHour; }
    public void setAvgCheckInHour(double avgCheckInHour) { this.avgCheckInHour = avgCheckInHour; }

    public double getAvgCheckOutHour() { return avgCheckOutHour; }
    public void setAvgCheckOutHour(double avgCheckOutHour) { this.avgCheckOutHour = avgCheckOutHour; }

    public double getAvgWorkHours() { return avgWorkHours; }
    public void setAvgWorkHours(double avgWorkHours) { this.avgWorkHours = avgWorkHours; }

    public double getKpiScore() { return kpiScore; }
    public void setKpiScore(double kpiScore) { this.kpiScore = kpiScore; }
}