package ru.kazantsev.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.kazantsev.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kazantsev.MyThirdTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService
{
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void isUnsupported(String uid) throws UnsupportedCodeException;
}
