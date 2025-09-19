package ru.kazantsev.MyFifthTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Positions;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Request;

@Service
public interface AnnualBonusService
{
    double calculate(Request request);

    double calculateQuarterlyBonus(Request request);

    int getDaysInYear(int year);
}
