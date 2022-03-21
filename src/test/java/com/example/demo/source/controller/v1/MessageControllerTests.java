package com.example.demo.source.controller.v1;

import com.example.demo.source.controller.v1.MessageController;
import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;
import com.example.demo.source.service.MessageService;
import com.example.demo.source.util.SayHello;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTests {

    @MockBean
    private MessageService messageService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testDoTranslateNumberToWord() throws Exception {
        int number = 5;
        TranslateResult expectedResult = TranslateResult.builder().id(number).name("Five").build();

        Mockito.when(messageService.doTranslateNumberToWord(anyInt())).thenReturn(expectedResult);

        mockMvc.perform(get("/api/v1/translate/number-to-word/" + number))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(expectedResult.getName())))
                .andExpect(jsonPath("$.id", Matchers.is(expectedResult.getId())));
    }


    @Test
    public void testSayHello() throws Exception {
        int number = 5;
        SayHelloResult expectedResult = SayHelloResult.builder().id(number).result("Hello!Hello!Hello!Hello!Hello").build();

        Mockito.when(messageService.sayHello(anyInt())).thenReturn(expectedResult);

        mockMvc.perform(get("/api/v1/say-hello/" + number))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", Matchers.is(expectedResult.getResult())))
                .andExpect(jsonPath("$.id", Matchers.is(expectedResult.getId())));


    }


} 
