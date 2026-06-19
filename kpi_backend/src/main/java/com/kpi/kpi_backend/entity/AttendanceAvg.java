package com.kpi.kpi_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "V_ATTENDANCE_AVG_TIMES")
public class AttendanceAvg {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "AVG_CHECK_IN_HOUR")
    private Double avgCheckInHour;

    @Column(name = "AVG_CHECK_OUT_HOUR")
    private Double avgCheckOutHour;

    // Constructors
    public AttendanceAvg() {}

    public AttendanceAvg(Long employeeId, Double avgCheckInHour, Double avgCheckOutHour) {
        this.employeeId = employeeId;
        this.avgCheckInHour = avgCheckInHour;
        this.avgCheckOutHour = avgCheckOutHour;
    }

    // Getters and Setters
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Double getAvgCheckInHour() { return avgCheckInHour; }
    public void setAvgCheckInHour(Double avgCheckInHour) { this.avgCheckInHour = avgCheckInHour; }

    public Double getAvgCheckOutHour() { return avgCheckOutHour; }
    public void setAvgCheckOutHour(Double avgCheckOutHour) { this.avgCheckOutHour = avgCheckOutHour; }
}