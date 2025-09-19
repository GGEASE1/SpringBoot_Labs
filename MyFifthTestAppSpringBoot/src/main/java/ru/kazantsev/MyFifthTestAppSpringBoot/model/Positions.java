package ru.kazantsev.MyFifthTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum Positions
{
    DEV("Developer", false),
    HR("HR-specialist", false),
    TL("Team Lead", true),

    DIR("Director", true),
    QA("Quality Assurance", false),
    PM("Project Manager", true),;

    private final String name;
    private final boolean isManager;

    Positions(String name, boolean isManager)
    {
        this.name = name;
        this.isManager = isManager;
    }

    @JsonValue
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @JsonCreator
    public static Positions fromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        for(Positions system : Positions.values())
        {
            if(system.name().equalsIgnoreCase(value) || system.getName().equalsIgnoreCase(value))
            {
                log.info("Роль: " + system.name);
                return system;
            }
        }
        log.error("В поле positions можно указать только DEV,HR,TL,DIR,QA,PM");
        throw new IllegalArgumentException("Unknown Position value: " + value);
    }

}