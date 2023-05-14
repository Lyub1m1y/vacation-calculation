package com.calculation.vacation;

import com.calculation.vacation.controller.VacationCalculatorController;
import com.calculation.vacation.service.RussianHolidayService;
import com.calculation.vacation.service.VacationCalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class VacationCalculationApplicationTests {

    @Autowired
    private VacationCalculatorController controller;

    @Autowired
    private RussianHolidayService holidayService;

    @Autowired
    private VacationCalculationService service;

    @Test
    void contextLoads() {
        assertNotNull(controller);
        assertNotNull(holidayService);
        assertNotNull(service);
    }
}
