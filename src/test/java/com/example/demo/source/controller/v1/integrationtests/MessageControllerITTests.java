package com.example.demo.source.controller.v1.integrationtests;

import com.example.demo.DemoApplication;
import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;
import com.example.demo.source.service.MessageService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerITTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testDoTranslateNumberToWord() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        int number = 5;

        ResponseEntity<String> response = restTemplate.exchange( createURLWithPort("/api/v1/translate/number-to-word/" + number), HttpMethod.GET, entity, String.class);

        String expected = "{id:5,name:Five}";

        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    @Test
    public void testSayHello() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        int number = 3;

        ResponseEntity<String> response = restTemplate.exchange( createURLWithPort("/api/v1/say-hello/" + number), HttpMethod.GET, entity, String.class);

        String expected = "{id:3,result:Hello!Hello!Hello!}";

        JSONAssert.assertEquals(expected, response.getBody(), true);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


} 
