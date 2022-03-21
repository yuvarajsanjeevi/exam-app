package com.example.demo.source.controller;

import com.example.demo.source.IntToWord;
import com.example.demo.source.SayHello;
import com.example.demo.source.SayHelloResult;
import com.example.demo.source.TranslateResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/dotranslate/{value}")
    public TranslateResult doTranslate(@PathVariable Integer value) {

        TranslateResult result = new TranslateResult();
        result.setId(value);
        result.setName(IntToWord.Translate(value));

        return result;
    }

    @GetMapping("/sayhello/{value}")
    public SayHelloResult sayhello(@PathVariable Integer value) {

        SayHelloResult result = new SayHelloResult();
        result.setId(value);
        result.setResult(SayHello.SayXHello(value));

        return result;
    }
}
