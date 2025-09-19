package ru.kazantsev.MyFifthTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService
{
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
