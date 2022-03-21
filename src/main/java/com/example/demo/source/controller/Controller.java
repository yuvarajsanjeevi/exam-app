package com.example.demo.source.controller;

import com.example.demo.source.IntToWord;
import com.example.demo.source.SayHello;
import com.example.demo.source.SayHelloResult;
import com.example.demo.source.TranslateResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * changed url mapping
 * renamed static method IntToWord.translate, SayHello.sayXHello
 *
 */
@RestController
public class Controller {

    @GetMapping("/translate/number-to-word/{value}")
    public TranslateResult doTranslate(@PathVariable Integer value) {

        TranslateResult result = new TranslateResult();
        result.setId(value);
        // rename static method by convention
        result.setName(IntToWord.translate(value));

        return result;
    }

    @GetMapping("/say-hello/{value}")
    public SayHelloResult sayHello(@PathVariable Integer value) {

        SayHelloResult result = new SayHelloResult();
        result.setId(value);
        // rename static method by convention
        result.setResult(SayHello.sayXHello(value));

        return result;
    }
}
