package com.example.demo.source.service;

import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;

/**
 * @author yuvaraj sanjeevi
 */
public interface MessageService {

    TranslateResult doTranslateNumberToWord(Integer number);

    SayHelloResult sayHello(Integer noOfTimes);
}
