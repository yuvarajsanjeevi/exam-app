package com.example.demo.source.controller.v1;

import com.example.demo.source.util.IntToWord;
import com.example.demo.source.util.SayHello;
import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * changed url mapping
 * renamed static method IntToWord.translate, SayHello.sayXHello
 *
 */
@RestController
@RequestMapping("api/v1")
public class MessageController {

    @GetMapping("/translate/number-to-word/{value}")
    public TranslateResult doTranslate(@PathVariable Integer value) {
        return TranslateResult
                .builder()
                .id(value)
                .name(IntToWord.translate(value))
                .build();
    }

    @GetMapping("/say-hello/{value}")
    public SayHelloResult sayHello(@PathVariable Integer value) {

        return SayHelloResult
                .builder()
                .id(value)
                .result(SayHello.sayXHello(value))
                .build();
    }
}
