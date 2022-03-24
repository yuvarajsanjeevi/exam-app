package com.example.demo.source.controller.v1.integrationtests;

import com.example.demo.DemoApplication;
import com.example.demo.source.dto.CustomerDTO;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerITTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testAddCustomer() throws Exception {

        CustomerDTO customerDTO = CustomerDTO.builder().age("15").name("Test2").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(customerDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/customers"), HttpMethod.POST, entity, String.class);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        Assert.assertThat(documentContext.read("$.age"), Matchers.is(customerDTO.getAge()));
        Assert.assertThat(documentContext.read("$.name"), Matchers.is(customerDTO.getName()));

        Assert.assertThat(documentContext.read("$.id"), Matchers.notNullValue());
        Assert.assertThat(documentContext.read("$.createdAt"), Matchers.notNullValue());

    }

    @Test
    public void testGetCustomers() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/customers"), HttpMethod.GET, entity, String.class);

        Assert.assertSame(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetCustomerById_NotFound() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);

        long id = Long.MAX_VALUE;

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/customers/" + id), HttpMethod.GET, entity, String.class);

        Assert.assertSame(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetCustomerById_Success() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);

        long id = 1;

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/customers/" + id), HttpMethod.GET, entity, String.class);

        Assert.assertSame(response.getStatusCode(), HttpStatus.OK);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


} 
