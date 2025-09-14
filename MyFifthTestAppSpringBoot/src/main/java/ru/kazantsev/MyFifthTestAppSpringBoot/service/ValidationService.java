package ru.kazantsev.MyFifthTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.kazantsev.MyFifthTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kazantsev.MyFifthTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService
{
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void isUnsupported(String uid) throws UnsupportedCodeException;
}
