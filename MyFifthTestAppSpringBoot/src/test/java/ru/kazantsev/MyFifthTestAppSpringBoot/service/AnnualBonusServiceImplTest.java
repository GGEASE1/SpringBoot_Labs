package ru.kazantsev.MyFifthTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Positions;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AnnualBonusServiceImplTest
{

    @InjectMocks
    private AnnualBonusServiceImpl annualBonusService;

    @Test
    void calculate()
    {
        // Arrange
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.DEV);
        // Act
        double result = annualBonusService.calculate(request);
        // Assert
        // Ожидаемый результат: (100000 * 2.3 * 250) / 365 = 157534.24657534246
        assertEquals(157534.24657534246, result, 0.001);
    }

    @Test
    void calculateQuarterlyBonusForManager()
    {
        // Arrange: Запрос для менеджера (TL)
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.TL); // Менеджер
        // Act
        double result = annualBonusService.calculateQuarterlyBonus(request);
        // Assert: Квартальная премия = 30% от годовой
        double expectedAnnualBonus = annualBonusService.calculate(request);
        double expectedQuarterlyBonus = expectedAnnualBonus * 0.3;
        assertEquals(expectedQuarterlyBonus, result, 0.001);
    }

    @Test
    void calculateQuarterlyBonusForNotManager()
    {
        // Arrange: Запрос для НЕ менеджера (DEV)
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.DEV); // Не менеджер
        // Act
        double result = annualBonusService.calculateQuarterlyBonus(request);
        // Assert: Для не менеджера должна вернуться 0
        assertEquals(0.0, result, 0.001);
    }

    @Test
    void getDaysInYearForLeapYear()
    {
        // Act
        int days = annualBonusService.getDaysInYear(2024); // 2024 - високосный
        // Assert
        assertEquals(366, days);
    }

    @Test
    void getDaysInYearForNonLeapYear()
    {
        // Act
        int days = annualBonusService.getDaysInYear(2023); // 2023 - не високосный
        // Assert
        assertEquals(365, days);
    }
}