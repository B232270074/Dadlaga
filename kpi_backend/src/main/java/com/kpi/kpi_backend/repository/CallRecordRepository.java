package com.kpi.kpi_backend.repository;

import com.kpi.kpi_backend.entity.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRecordRepository extends JpaRepository<CallRecord, Long> {

    List<CallRecord> findAllByEmployeeId(Long employeeId);
}
