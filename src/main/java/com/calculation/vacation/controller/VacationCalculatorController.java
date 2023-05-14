package com.calculation.vacation.controller;

import com.calculation.vacation.model.VacationCalculationRequest;
import com.calculation.vacation.model.VacationCalculationResponse;
import com.calculation.vacation.service.VacationCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VacationCalculatorController {

    private final VacationCalculationService calculationService;

    @Autowired
    public VacationCalculatorController(VacationCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculate")
    public VacationCalculationResponse calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) List<LocalDate> vacationPeriod
    ) {
        VacationCalculationRequest request = new VacationCalculationRequest(averageSalary, vacationDays, vacationPeriod);
        return calculationService.calculateVacationPay(request);
    }
}