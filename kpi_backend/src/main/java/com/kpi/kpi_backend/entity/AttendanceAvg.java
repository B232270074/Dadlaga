package com.kpi.kpi_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "V_ATTENDANCE_AVG_TIMES")
public class AttendanceAvg {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "AVG_CHECK_IN_HOUR")
    private Double avgCheckInHour;

    @Column(name = "AVG_CHECK_OUT_HOUR")
    private Double avgCheckOutHour;
}
