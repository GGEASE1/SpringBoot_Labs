package ru.kazantsev.MyThirdTestAppSpringBoot.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.Response;
import ru.kazantsev.MyThirdTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService
{
    @Override
    public Response modify(Response response)
    {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }
}
