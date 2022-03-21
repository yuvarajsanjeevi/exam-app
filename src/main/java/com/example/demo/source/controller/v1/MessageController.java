package com.example.demo.source.controller.v1;

import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;
import com.example.demo.source.service.MessageService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/translate/number-to-word/{number}")
    public TranslateResult doTranslateNumberToWord(@PathVariable Integer number) {
        return messageService.doTranslateNumberToWord(number);
    }

    @GetMapping("/say-hello/{noOfTimes}")
    public SayHelloResult sayHello(@PathVariable Integer noOfTimes) {
        return messageService.sayHello(noOfTimes);
    }
}
