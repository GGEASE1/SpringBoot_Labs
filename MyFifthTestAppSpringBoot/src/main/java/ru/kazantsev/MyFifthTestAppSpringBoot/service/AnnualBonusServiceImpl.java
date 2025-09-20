package ru.kazantsev.MyFifthTestAppSpringBoot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Request;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class AnnualBonusServiceImpl implements AnnualBonusService
{
    @Override
    public double calculate(Request request)
    {
        int daysInYear = getDaysInYear(Year.now().getValue());
        return (request.getSalary() * request.getBonus() * request.getWorkDays()) / daysInYear;
    }

    @Override
    public double calculateQuarterlyBonus(Request request)
    {

        if (!request.getPosition().isManager())
        {
            return 0.0;
        }

        double annualBonus = calculate(request);
        return annualBonus * 0.3;
    }

    @Override
    public int getDaysInYear(int year)
    {
        return Year.of(year).isLeap() ? 366 : 365;
    }

}
