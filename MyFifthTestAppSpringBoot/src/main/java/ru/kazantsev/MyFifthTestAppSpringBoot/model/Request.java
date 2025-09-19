package ru.kazantsev.MyFifthTestAppSpringBoot.model;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request
{
    @NotBlank(message = "Uid не может быть пустым")
    @Size(max = 32, message = "Uid обязательно, длина не более 32 символов")
    private String uid;

    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max = 32, message = "operationUid обязательно, длина не более 32 символов")
    private String operationUid;

    @NotNull(message = "Укажите тип системы: CRM, ERP, WMS")
    private Systems systemName;

    private String systemTime;

    private String source;

    @NotNull(message = "Position обязательна для заполнения")
    private Positions position;

    @Positive(message = "Salary должен быть положительным числом")
    @NotNull(message = "Salary обязателен для заполнения")
    private double salary;

    @Positive(message = "Bonus должен быть положительным числом")
    @NotNull(message = "Bonus обязателен для заполнения")
    private double bonus;

    @Positive(message = "WorkDays должно быть положительным числом")
    @Min(value = 1, message = "WorkDays не может быть меньше 1")
    @Max(value = 366, message = "WorkDays не может быть больше 366")
    @NotNull(message = "WorkDays обязателен для заполнения")
    private int workDays;

    @NotNull
    @Min(value = 1, message = "comuncationId минимальное значение: 1")
    @Max(value = 100000, message = "communicationId не может быть более: 100 000")
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

    private Long service1Time;

    @Override
    public String toString()
    {
        return "{" +
                "uid=" + uid + " " +
                ", operationUid=" + operationUid + " " +
                ", systemName=" + systemName + " " +
                ", systemTime=" + systemTime + " " +
                ", source=" + source + " " +
                ", communicationId=" + communicationId + " " +
                ", templateId=" + templateId + " " +
                ", producCode=" + productCode + " " +
                ", smsCode=" + smsCode +
                ", position=" + position +
                ", salary=" + salary + " " +
                ", bonus=" + bonus + " " +
                ", workDays=" + workDays + "}";
    }
}
