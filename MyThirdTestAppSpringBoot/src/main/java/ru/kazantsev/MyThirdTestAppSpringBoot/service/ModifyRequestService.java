package ru.kazantsev.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kazantsev.MyThirdTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService
{
    void modify(Request request);
}
