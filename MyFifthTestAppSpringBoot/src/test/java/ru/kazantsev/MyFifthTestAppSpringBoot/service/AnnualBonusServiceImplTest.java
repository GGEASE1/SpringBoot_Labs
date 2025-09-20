package ru.kazantsev.MyFifthTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Positions;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnualBonusServiceImplTest
{

    // Создаем экземпляр сервиса для тестирования
    private AnnualBonusServiceImpl annualBonusService = new AnnualBonusServiceImpl();

    @Test
    void calculate_WithValidData_ReturnsCorrectBonus()
    {
        // Arrange - подготавливаем данные
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.DEV);

        // Act - выполняем действие
        double result = annualBonusService.calculate(request);

        // Assert - проверяем результат
        // Ожидаемый результат: (100000 * 2.3 * 250) / 365 = 157534.24657534246
        assertEquals(157534.24657534246, result, 0.001);
    }

    @Test
    void calculateQuarterlyBonus_ForManager_ReturnsQuarterlyBonus()
    {
        // Arrange - запрос для менеджера (TL)
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.TL); // Менеджер

        // Act
        double result = annualBonusService.calculateQuarterlyBonus(request);

        // Assert - квартальная премия = 30% от годовой
        double expectedAnnualBonus = annualBonusService.calculate(request);
        double expectedQuarterlyBonus = expectedAnnualBonus * 0.3;
        assertEquals(expectedQuarterlyBonus, result, 0.001);
    }

    @Test
    void calculateQuarterlyBonus_ForNotManager_ReturnsZero()
    {
        // Arrange - запрос для НЕ менеджера (DEV)
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.DEV); // Не менеджер

        // Act
        double result = annualBonusService.calculateQuarterlyBonus(request);

        // Assert - для не менеджера должна вернуться 0
        assertEquals(0.0, result, 0.001);
    }

    @Test
    void getDaysInYear_ForLeapYear_Returns366()
    {
        // Act
        int days = annualBonusService.getDaysInYear(2024); // 2024 - високосный

        // Assert
        assertEquals(366, days);
    }

    @Test
    void getDaysInYear_ForNonLeapYear_Returns365()
    {
        // Act
        int days = annualBonusService.getDaysInYear(2023); // 2023 - не високосный

        // Assert
        assertEquals(365, days);
    }

    @Test
    void calculate_WithLeapYear_UsesCorrectDaysCount()
    {
        // Arrange
        Request request = new Request();
        request.setSalary(100000.0);
        request.setBonus(2.3);
        request.setWorkDays(250);
        request.setPosition(Positions.DEV);

        // Создаем сервис с подставным годом через наследование
        AnnualBonusService leapYearService = new AnnualBonusServiceImpl()
        {
            @Override
            public int getDaysInYear(int year)
            {
                return 366; // Всегда возвращаем високосный год
            }
        };

        // Act
        double result = leapYearService.calculate(request);

        // Assert: (100000 * 2.3 * 250) / 366 = 157103.825136612
        assertEquals(157103.825136612, result, 0.001);
    }
}