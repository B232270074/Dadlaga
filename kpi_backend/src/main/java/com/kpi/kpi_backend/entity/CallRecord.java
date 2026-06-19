package com.kpi.kpi_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KPI_TABLE")
public class CallRecord {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "RECEIVER_NUMB", length = 250)
    private String receiverNumb;

    @Column(name = "RINGING_TIME", length = 100)
    private String ringingTime;

    @Column(name = "AUDIO_FILE", length = 250)
    private String audioFile;

    @Column(name = "SERVICE_DURATION", length = 20)
    private String serviceDuration;

    @Column(name = "CALL_TYPE")
    private Integer callType;

    @Column(name = "CALLER_NUMB", length = 250)
    private String callerNumb;

    @Column(name = "CALL_DATE")
    private LocalDateTime callDate;

    @Column(name = "CALL_ID", length = 250)
    private String callId;

    @Column(name = "CALL_START_TIME")
    private LocalDateTime callStartTime;

    @Column(name = "CALL_END_TIME")
    private LocalDateTime callEndTime;

    @Column(name = "CALL_STATUS", length = 20)
    private String callStatus;

    @Column(name = "CALL_REG_STATUS")
    private Integer callRegStatus;
}
