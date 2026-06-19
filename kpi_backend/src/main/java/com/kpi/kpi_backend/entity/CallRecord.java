package com.kpi.kpi_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    // Constructors
    public CallRecord() {}

    public CallRecord(Long id, Long employeeId, String receiverNumb, String ringingTime, String audioFile,
                      String serviceDuration, Integer callType, String callerNumb, LocalDateTime callDate,
                      String callId, LocalDateTime callStartTime, LocalDateTime callEndTime,
                      String callStatus, Integer callRegStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.receiverNumb = receiverNumb;
        this.ringingTime = ringingTime;
        this.audioFile = audioFile;
        this.serviceDuration = serviceDuration;
        this.callType = callType;
        this.callerNumb = callerNumb;
        this.callDate = callDate;
        this.callId = callId;
        this.callStartTime = callStartTime;
        this.callEndTime = callEndTime;
        this.callStatus = callStatus;
        this.callRegStatus = callRegStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getReceiverNumb() { return receiverNumb; }
    public void setReceiverNumb(String receiverNumb) { this.receiverNumb = receiverNumb; }

    public String getRingingTime() { return ringingTime; }
    public void setRingingTime(String ringingTime) { this.ringingTime = ringingTime; }

    public String getAudioFile() { return audioFile; }
    public void setAudioFile(String audioFile) { this.audioFile = audioFile; }

    public String getServiceDuration() { return serviceDuration; }
    public void setServiceDuration(String serviceDuration) { this.serviceDuration = serviceDuration; }

    public Integer getCallType() { return callType; }
    public void setCallType(Integer callType) { this.callType = callType; }

    public String getCallerNumb() { return callerNumb; }
    public void setCallerNumb(String callerNumb) { this.callerNumb = callerNumb; }

    public LocalDateTime getCallDate() { return callDate; }
    public void setCallDate(LocalDateTime callDate) { this.callDate = callDate; }

    public String getCallId() { return callId; }
    public void setCallId(String callId) { this.callId = callId; }

    public LocalDateTime getCallStartTime() { return callStartTime; }
    public void setCallStartTime(LocalDateTime callStartTime) { this.callStartTime = callStartTime; }

    public LocalDateTime getCallEndTime() { return callEndTime; }
    public void setCallEndTime(LocalDateTime callEndTime) { this.callEndTime = callEndTime; }

    public String getCallStatus() { return callStatus; }
    public void setCallStatus(String callStatus) { this.callStatus = callStatus; }

    public Integer getCallRegStatus() { return callRegStatus; }
    public void setCallRegStatus(Integer callRegStatus) { this.callRegStatus = callRegStatus; }
}