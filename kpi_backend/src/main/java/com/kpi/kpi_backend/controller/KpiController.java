package com.kpi.kpi_backend.controller;

import com.kpi.kpi_backend.dto.EmployeeKpiDto;
import com.kpi.kpi_backend.service.KpiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/kpis")
@RequiredArgsConstructor
public class KpiController {

    private final KpiService kpiService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeKpiDto>> getAllEmployeeKpis() {
        return ResponseEntity.ok(kpiService.getAllEmployeeKpis());
    }
}
