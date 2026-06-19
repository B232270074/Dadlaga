package com.kpi.kpi_backend.service;

import com.kpi.kpi_backend.dto.EmployeeKpiDto;
import com.kpi.kpi_backend.entity.AttendanceAvg;
import com.kpi.kpi_backend.entity.CallRecord;
import com.kpi.kpi_backend.repository.AttendanceAvgRepository;
import com.kpi.kpi_backend.repository.CallRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KpiService {

    private final CallRecordRepository callRecordRepository;
    private final AttendanceAvgRepository attendanceAvgRepository;

    private static final Map<Long, String> EMPLOYEE_NAMES = Map.of(
            1L, "Бат-Эрдэнэ",
            2L, "Сарангэрэл",
            3L, "Тэмүүлэн",
            4L, "Оюунчимэг",
            5L, "Ганбаатар",
            6L, "Номин-Эрдэнэ",
            7L, "Баярмаа",
            8L, "Энхжаргал",
            9L, "Мөнхбат"
    );

    private static final double WEIGHT_ANSWERED_CALLS = 25.0;
    private static final double WEIGHT_AVG_TALK_TIME = 20.0;
    private static final double WEIGHT_MISSED_CALLS = 15.0;
    private static final double WEIGHT_REG_STATUS = 10.0;
    private static final double WEIGHT_RINGING_TIME = 15.0;
    private static final double WEIGHT_ATTENDANCE = 15.0;

    public List<EmployeeKpiDto> getAllEmployeeKpis() {
        try {
            List<CallRecord> allRecords = callRecordRepository.findAll();
            Map<Long, AttendanceAvg> attendanceMap = attendanceAvgRepository.findAll()
                    .stream().collect(Collectors.toMap(AttendanceAvg::getEmployeeId, a -> a));

            Map<Long, List<CallRecord>> byEmployee = allRecords.stream()
                    .filter(r -> r.getEmployeeId() != null)
                    .collect(Collectors.groupingBy(CallRecord::getEmployeeId));

            long globalMaxAnswered = 0;
            double globalMaxAvgRinging = 0;

            Map<Long, EmployeeKpiDto> dtoMap = new LinkedHashMap<>();
            for (long empId = 1; empId <= 9; empId++) {
                List<CallRecord> records = byEmployee.getOrDefault(empId, List.of());
                EmployeeKpiDto dto = computeMetrics(empId, records, attendanceMap.get(empId));
                dtoMap.put(empId, dto);
                if (dto.getAnsweredCalls() > globalMaxAnswered) globalMaxAnswered = dto.getAnsweredCalls();
                if (dto.getAvgAnswerSpeedSec() > globalMaxAvgRinging) globalMaxAvgRinging = dto.getAvgAnswerSpeedSec();
            }

            final long maxAnswered = globalMaxAnswered;
            final double maxRinging = globalMaxAvgRinging;
            for (EmployeeKpiDto dto : dtoMap.values()) {
                double score = computeKpiScore(dto, maxAnswered, maxRinging);
                dto.setKpiScore(Math.round(score * 100.0) / 100.0);
            }

            return new ArrayList<>(dtoMap.values());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("KPI алдаа: " + e.getMessage(), e);
        }
    }

    private EmployeeKpiDto computeMetrics(Long empId, List<CallRecord> records, AttendanceAvg attendance) {
        EmployeeKpiDto dto = new EmployeeKpiDto();
        dto.setEmployeeId(empId);
        dto.setEmployeeName(EMPLOYEE_NAMES.getOrDefault(empId, "Оператор " + empId));

        // Хэрэв records null байвал хоосон list болгох
        if (records == null) records = List.of();

        List<CallRecord> comeRecords = records.stream()
                .filter(r -> "COME".equalsIgnoreCase(r.getCallStatus()))
                .toList();
        List<CallRecord> outRecords = records.stream()
                .filter(r -> "OUT".equalsIgnoreCase(r.getCallStatus()))
                .toList();
        List<CallRecord> unsuccessfulRecords = records.stream()
                .filter(r -> "UNSUCCESSFUL".equalsIgnoreCase(r.getCallStatus()))
                .toList();

        dto.setTotalIncomingCalls(records.size());
        dto.setAnsweredCalls(comeRecords.size());
        dto.setOtherCalls(outRecords.size());
        dto.setMissedCalls(unsuccessfulRecords.size());

        long inTalkSec = comeRecords.stream().mapToLong(this::computeTalkTimeSec).sum();
        dto.setIncomingTalkTimeSec(inTalkSec);
        dto.setIncomingAvgTalkTimeSec(comeRecords.isEmpty() ? 0 : (double) inTalkSec / comeRecords.size());

        long inServiceSec = comeRecords.stream().mapToLong(this::parseServiceDuration).sum();
        dto.setIncomingServiceTimeSec(inServiceSec);
        dto.setIncomingAvgServiceTimeSec(comeRecords.isEmpty() ? 0 : (double) inServiceSec / comeRecords.size());

        dto.setMissedCallRate(records.isEmpty() ? 0 :
                Math.round((double) unsuccessfulRecords.size() / records.size() * 100.0 * 100.0) / 100.0);

        double avgRinging = comeRecords.stream()
                .mapToDouble(r -> parseDouble(r.getRingingTime()))
                .average().orElse(0);
        dto.setAvgAnswerSpeedSec(Math.round(avgRinging * 100.0) / 100.0);

        dto.setOutgoingCalls(outRecords.size());

        long outTalkSec = outRecords.stream().mapToLong(this::computeTalkTimeSec).sum();
        dto.setOutgoingTalkTimeSec(outTalkSec);
        dto.setOutgoingAvgTalkTimeSec(outRecords.isEmpty() ? 0 : (double) outTalkSec / outRecords.size());

        long outServiceSec = outRecords.stream().mapToLong(this::parseServiceDuration).sum();
        dto.setOutgoingServiceTimeSec(outServiceSec);
        dto.setOutgoingAvgServiceTimeSec(outRecords.isEmpty() ? 0 : (double) outServiceSec / outRecords.size());

        long regSuccess = records.stream()
                .filter(r -> r.getCallRegStatus() != null && r.getCallRegStatus() == 1).count();
        long regFail = records.stream()
                .filter(r -> r.getCallRegStatus() != null && r.getCallRegStatus() == 0).count();
        dto.setRegStatusSuccess(regSuccess);
        dto.setRegStatusFail(regFail);
        dto.setRegSuccessRate(records.isEmpty() ? 0 :
                Math.round((double) regSuccess / records.size() * 100.0 * 100.0) / 100.0);

        if (attendance != null) {
            dto.setAvgCheckInHour(attendance.getAvgCheckInHour() != null ? attendance.getAvgCheckInHour() : 0);
            dto.setAvgCheckOutHour(attendance.getAvgCheckOutHour() != null ? attendance.getAvgCheckOutHour() : 0);
            dto.setAvgWorkHours(Math.round((dto.getAvgCheckOutHour() - dto.getAvgCheckInHour()) * 100.0) / 100.0);
        }

        return dto;
    }

    private double computeKpiScore(EmployeeKpiDto dto, long maxAnswered, double maxRinging) {
        double score = 0;

        if (maxAnswered > 0) {
            score += WEIGHT_ANSWERED_CALLS * ((double) dto.getAnsweredCalls() / maxAnswered);
        }

        double avgTalk = dto.getIncomingAvgTalkTimeSec();
        if (avgTalk <= 120) {
            score += WEIGHT_AVG_TALK_TIME;
        } else {
            double excessRatio = Math.min((avgTalk - 120) / 180.0, 1.0);
            score += WEIGHT_AVG_TALK_TIME * (1.0 - excessRatio);
        }

        double missedRate = dto.getMissedCallRate();
        score += WEIGHT_MISSED_CALLS * Math.max(0, (100.0 - missedRate) / 100.0);

        score += WEIGHT_REG_STATUS * (dto.getRegSuccessRate() / 100.0);

        if (maxRinging > 0 && dto.getAvgAnswerSpeedSec() > 0) {
            double ringingRatio = dto.getAvgAnswerSpeedSec() / maxRinging;
            score += WEIGHT_RINGING_TIME * (1.0 - ringingRatio);
        } else {
            score += WEIGHT_RINGING_TIME;
        }

        double workHours = dto.getAvgWorkHours();
        if (workHours >= 9.0) {
            double bonusRatio = Math.min(workHours / 9.0, 10.5 / 9.0);
            score += WEIGHT_ATTENDANCE * Math.min(bonusRatio, 1.167);
        } else if (workHours > 0) {
            score += WEIGHT_ATTENDANCE * (workHours / 9.0);
        }

        return Math.min(score, 100.0);
    }

    private long computeTalkTimeSec(CallRecord r) {
        if (r == null || r.getCallStartTime() == null || r.getCallEndTime() == null) return 0;
        Duration d = Duration.between(r.getCallStartTime(), r.getCallEndTime());
        return Math.max(d.getSeconds(), 0);
    }

    private long parseServiceDuration(CallRecord r) {
        if (r == null) return 0;
        return (long) parseDouble(r.getServiceDuration());
    }

    private double parseDouble(String val) {
        if (val == null || val.isBlank()) return 0;
        try {
            // Зөвхөн цифр болон цэгийг үлдээх
            String cleaned = val.replaceAll("[^0-9.]", "");
            if (cleaned.isEmpty() || cleaned.equals(".")) return 0;
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}