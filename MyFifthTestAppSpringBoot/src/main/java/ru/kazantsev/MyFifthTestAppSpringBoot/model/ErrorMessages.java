package ru.kazantsev.MyFifthTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages
{
    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSUPPORTED("Не предвиденная ошибка"),
    UNKNOWN("Не поддерживаемая ошибка"),
    CALCULATION("Ошибка расчета");

    private final String description;

    ErrorMessages(String description)
    {
        this.description = description;
    }
    @JsonValue
    public String getName()
    {
        return description;
    }
}
