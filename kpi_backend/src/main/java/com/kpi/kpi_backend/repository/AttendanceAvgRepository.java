package com.kpi.kpi_backend.repository;

import com.kpi.kpi_backend.entity.AttendanceAvg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceAvgRepository extends JpaRepository<AttendanceAvg, Long> {
}
