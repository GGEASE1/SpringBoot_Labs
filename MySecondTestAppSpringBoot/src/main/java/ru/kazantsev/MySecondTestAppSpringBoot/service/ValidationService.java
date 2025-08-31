package ru.kazantsev.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.kazantsev.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kazantsev.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService
{
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void isUnsupported(String uid) throws UnsupportedCodeException;
}
