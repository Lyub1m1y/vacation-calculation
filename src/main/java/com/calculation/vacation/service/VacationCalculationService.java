package com.calculation.vacation.service;

import com.calculation.vacation.model.VacationCalculationRequest;
import com.calculation.vacation.model.VacationCalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class VacationCalculationService {

    private static final double AVERAGE_MONTH = 29.3;
    private final RussianHolidayService holidayService;

    @Autowired
    public VacationCalculationService(RussianHolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public VacationCalculationResponse calculateVacationPay(VacationCalculationRequest request) {
        int vacationDays = request.getVacationDays();
        List<LocalDate> vacationPeriod = request.getVacationPeriod();

        if (vacationDays < 1 && vacationPeriod == null) {
            throw new IllegalArgumentException("Vacation days must not be less than one day");
        }

        if (vacationPeriod != null) {
            if (vacationPeriod.size() == 1 || vacationPeriod.size() == 2) {
                vacationDays = countVacationDays(vacationPeriod);
            } else {
                throw new IllegalArgumentException("The vacation period must consist of one or two dates");
            }
        }

        return new VacationCalculationResponse((int) Math.round(request.getAverageSalary() / AVERAGE_MONTH * vacationDays));
    }

    private int countVacationDays(List<LocalDate> vacationPeriod) {
        int vacationDays = 0;
        LocalDate currentDate = vacationPeriod.get(0);
        LocalDate endDate;
        if (vacationPeriod.size() == 2) {
            endDate = vacationPeriod.get(1);
        } else {
            endDate = currentDate;
        }

        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !holidayService.isHoliday(currentDate)) {
                vacationDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return vacationDays;
    }
}