package ru.kazantsev.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.kazantsev.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kazantsev.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService
{

    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if(bindingResult.hasErrors())
        {
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    public void isUnsupported(String uid) throws UnsupportedCodeException{
        if(uid.equals("123")){
            throw new UnsupportedCodeException("Не допустимое значение uid!");
        }

    }
}
