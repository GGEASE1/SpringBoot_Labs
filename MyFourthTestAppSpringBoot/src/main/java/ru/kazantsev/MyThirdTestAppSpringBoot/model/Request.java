package ru.kazantsev.MyThirdTestAppSpringBoot.model;

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

    @NotNull
    @Min(value = 1, message = "comuncationId минимальное значение: 1")
    @Max(value = 100000, message = "communicationId не может быть более: 100 000")
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

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
                ", smsCode=" + smsCode + " }";
    }
}
