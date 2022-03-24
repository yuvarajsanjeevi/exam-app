package com.example.demo.source.service.impl;

import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;
import com.example.demo.source.service.MessageService;
import com.example.demo.source.util.IntToWord;
import com.example.demo.source.util.SayHello;
import org.springframework.stereotype.Service;

/**
 * @author yuvaraj sanjeevi
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public TranslateResult doTranslateNumberToWord(Integer number) {
        return TranslateResult
                .builder()
                .id(number)
                .name(IntToWord.translate(number))
                .build();
    }

    @Override
    public SayHelloResult sayHello(Integer noOfTimes) {
        return SayHelloResult
                .builder()
                .id(noOfTimes)
                .result(SayHello.sayXHello(noOfTimes))
                .build();
    }
}
