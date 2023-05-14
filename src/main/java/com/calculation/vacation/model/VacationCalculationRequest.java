package com.calculation.vacation.model;

import java.time.LocalDate;
import java.util.List;

public class VacationCalculationRequest {
    private double averageSalary;
    private int vacationDays;
    private List<LocalDate> vacationPeriod;

    public VacationCalculationRequest(double averageSalary, int vacationDays, List<LocalDate> exactVacationDays) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.vacationPeriod = exactVacationDays;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public List<LocalDate> getVacationPeriod() {
        return vacationPeriod;
    }
}