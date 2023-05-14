package com.calculation.vacation.model;

public class VacationCalculationResponse {
    private int vacationPay;


    public VacationCalculationResponse(int vacationPay) {
        this.vacationPay = vacationPay;
    }

    public int getVacationPay() {
        return vacationPay;
    }
}