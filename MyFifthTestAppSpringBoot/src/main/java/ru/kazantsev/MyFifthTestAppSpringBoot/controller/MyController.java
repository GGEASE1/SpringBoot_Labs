package ru.kazantsev.MyFifthTestAppSpringBoot.controller;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ru.kazantsev.MyFifthTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kazantsev.MyFifthTestAppSpringBoot.exception.ValidationFailedException;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Codes;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.ErrorCodes;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.ErrorMessages;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Request;
import ru.kazantsev.MyFifthTestAppSpringBoot.model.Response;
import ru.kazantsev.MyFifthTestAppSpringBoot.service.ModifyResponseService;
import ru.kazantsev.MyFifthTestAppSpringBoot.service.ValidationService;
import ru.kazantsev.MyFifthTestAppSpringBoot.util.DateTimeUtil;
import ru.kazantsev.MyFifthTestAppSpringBoot.service.AnnualBonusService;

@Slf4j
@RestController

public class MyController
{
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final AnnualBonusService annualBonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")
                        ModifyResponseService modifyResponseService,
                        AnnualBonusService annualBonusService)
    {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(
            @Valid @RequestBody Request request,
            BindingResult bindingResult
    )
    {

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try
        {
            validationService.isUnsupported(request.getUid());
            validationService.isValid(bindingResult);
        }
        catch(ValidationFailedException e)
        {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (UnsupportedCodeException e)
        {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
            modifyResponseService.modify(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/calculate")
    public ResponseEntity<Response> calculate
            (@Valid @RequestBody Request request)
    {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try
        {
            double annualBonus = annualBonusService.calculate(request);
            response.setAnnualBonus(annualBonus);

            double quarterlyBonus = annualBonusService.calculateQuarterlyBonus(request);
            response.setQuarterlyBonus(quarterlyBonus);

            response.setCode(Codes.SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.CALCULATION_ERROR);
            response.setErrorMessage(ErrorMessages.CALCULATION);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

