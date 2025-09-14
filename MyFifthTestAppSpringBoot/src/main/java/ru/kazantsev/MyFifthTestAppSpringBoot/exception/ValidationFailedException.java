package ru.kazantsev.MyFifthTestAppSpringBoot.exception;

public class ValidationFailedException extends Exception
{
    public ValidationFailedException(String message)
    {
        super(message);
    }
}