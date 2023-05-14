package com.calculation.vacation.service;

import com.calculation.vacation.model.VacationCalculationRequest;
import com.calculation.vacation.model.VacationCalculationResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VacationCalculationServiceTest {
    private static VacationCalculationService service;

    @BeforeAll
    static void setUp() {
        service = new VacationCalculationService(new RussianHolidayService());
    }

    @Test
    void countVacationDays_whenVacationDaysLessThanOne_And_VacationPeriodEqualsNullTest() {
        VacationCalculationRequest request = new VacationCalculationRequest(100000, 0, null);

        Exception exception = assertThrowsExactly(IllegalArgumentException.class, () -> service.calculateVacationPay(request));
        assertTrue(exception.getMessage().contains("Vacation days must not be less than one day"));

    }

    @Test
    void countVacationDays_whenExistOneDay_And_VacationPeriodEqualsNullTest() {
        VacationCalculationRequest request = new VacationCalculationRequest(100000, 1, null);

        VacationCalculationResponse response = service.calculateVacationPay(request);

        assertEquals(3413, response.getVacationPay());

    }

    @Test
    void countVacationDays_whenExistOneDayTest() {
        List<LocalDate> vacationPeriod = Arrays.asList(LocalDate.of(2023, 5, 10));
        VacationCalculationRequest request = new VacationCalculationRequest(100001, 28, vacationPeriod);

        VacationCalculationResponse response = service.calculateVacationPay(request);

        assertEquals(3413, response.getVacationPay());
    }

    @Test
    void countVacationDays_whenExistTwoDaysTest() {
        List<LocalDate> vacationPeriod = Arrays.asList(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 11));
        VacationCalculationRequest request = new VacationCalculationRequest(100000, -1, vacationPeriod);

        VacationCalculationResponse response = service.calculateVacationPay(request);

        assertEquals(6826, response.getVacationPay());
    }

    @Test
    void countVacationDays_whenExistTwoOrMoreDaysTest() {
        List<LocalDate> vacationPeriod = Arrays.asList(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 6, 10), LocalDate.of(2023, 7, 10));
        VacationCalculationRequest request = new VacationCalculationRequest(100000, 28, vacationPeriod);

        Exception exception = assertThrowsExactly(IllegalArgumentException.class, () -> service.calculateVacationPay(request));
        assertTrue(exception.getMessage().contains("The vacation period must consist of one or two dates"));
    }


    @Test
    void countVacationDaysInOctoberTest() {
        List<LocalDate> vacationPeriod = Arrays.asList(LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 31));

        int result = ReflectionTestUtils.invokeMethod(service, "countVacationDays", vacationPeriod);

        assertEquals(22, result);
    }
}