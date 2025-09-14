package ru.kazantsev.MyThirdTestAppSpringBoot.controller;

import java.util.Date;

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
import ru.kazantsev.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kazantsev.MyThirdTestAppSpringBoot.exception.ValidationFailedException;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.Codes;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.ErrorCodes;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.ErrorMessages;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.Request;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.Response;
import ru.kazantsev.MyThirdTestAppSpringBoot.service.ModifyRequestService;
import ru.kazantsev.MyThirdTestAppSpringBoot.service.ModifyResponseService;
import ru.kazantsev.MyThirdTestAppSpringBoot.service.ValidationService;
import ru.kazantsev.MyThirdTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
@RestController
public class MyController
{
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")
                        ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService)
    {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(
            @Valid @RequestBody Request request,
            BindingResult bindingResult
    )
    {
        request.setService1Time(System.currentTimeMillis());

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
            modifyRequestService.modify(request);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
