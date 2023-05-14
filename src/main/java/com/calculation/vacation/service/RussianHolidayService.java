package com.calculation.vacation.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class RussianHolidayService {
    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();

    public RussianHolidayService() {
        HOLIDAYS.add(LocalDate.of(2023, 1, 1));  // New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 2));  // Day after New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 3));  // Day off for New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 4));  // Day off for New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 5));  // Day off for New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 6));  // Day off for New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 7));  // Christmas Day
        HOLIDAYS.add(LocalDate.of(2023, 1, 8));  // Day off for New Year's Day
        HOLIDAYS.add(LocalDate.of(2023, 2, 23)); // Defender of the Fatherland Day
        HOLIDAYS.add(LocalDate.of(2023, 3, 8));  // International Women's Day
        HOLIDAYS.add(LocalDate.of(2023, 5, 1));  // Spring and Labor Day
        HOLIDAYS.add(LocalDate.of(2023, 5, 9));  // Victory Day
        HOLIDAYS.add(LocalDate.of(2023, 6, 12)); // Russia Day
        HOLIDAYS.add(LocalDate.of(2023, 11, 4)); // National Unity Day
    }

    public boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }
}
