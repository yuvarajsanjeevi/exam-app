package com.example.demo.source.controller.v1;

import com.example.demo.source.controller.v1.PersonController;
import com.example.demo.source.dto.CustomerDTO;
import com.example.demo.source.dto.PersonDTO;
import com.example.demo.source.entity.Customer;
import com.example.demo.source.repository.CustomerRepository;
import com.example.demo.source.service.CustomerService;
import com.example.demo.source.service.PersonService;
import com.example.demo.source.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.hamcrest.core.AnyOf;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @MockBean
    private CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testAddCustomer_WhenNameNull_Return400() throws Exception {

        CustomerDTO customerDTO = CustomerDTO.builder().age("15").build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsString("name must not be blank")));

    }

    @Test
    public void testAddCustomer_WhenAgeNull_Return400() throws Exception {

        CustomerDTO customerDTO = CustomerDTO.builder().name("").build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message", Matchers.containsString("age must not be blank")));

    }

    @Test
    public void testAddCustomer_Success() throws Exception {

        CustomerDTO customerDTO = CustomerDTO.builder().age("15").name("Test").build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(customerDTO);


        customerDTO.setCreatedAt(new Date());
        customerDTO.setId(3L);
        Mockito.when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(customerDTO.getId().intValue())))
                .andExpect(jsonPath("$.age", Matchers.is(customerDTO.getAge())))
                .andExpect(jsonPath("$.name", Matchers.is(customerDTO.getName())))
                .andExpect(jsonPath("$.createdAt", Matchers.notNullValue()));

    }



    @Test
    public void testGetCustomerList() throws Exception {

        CustomerDTO customerDTO1 = CustomerDTO.builder().id(1L).name("Test1").age("15").createdAt(new Date()).build();
        CustomerDTO customerDTO2 = CustomerDTO.builder().id(1L).name("Test2").age("19").createdAt(new Date()).build();
        CustomerDTO customerDTO3 = CustomerDTO.builder().id(1L).name("Test3").age("20").createdAt(new Date()).build();
        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO1, customerDTO2, customerDTO3);

        Mockito.when(customerService.getAllCustomers()).thenReturn(customerDTOList);

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))

                .andExpect(jsonPath("$[0].id", Matchers.is(customerDTO1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", Matchers.is(customerDTO1.getName())))
                .andExpect(jsonPath("$[0].age", Matchers.is(customerDTO1.getAge())))
                .andExpect(jsonPath("$[0].createdAt", Matchers.notNullValue()))


                .andExpect(jsonPath("$[1].id", Matchers.is(customerDTO2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", Matchers.is(customerDTO2.getName())))
                .andExpect(jsonPath("$[1].age", Matchers.is(customerDTO2.getAge())))
                .andExpect(jsonPath("$[1].createdAt", Matchers.notNullValue()))

                .andExpect(jsonPath("$[2].id", Matchers.is(customerDTO3.getId().intValue())))
                .andExpect(jsonPath("$[2].name", Matchers.is(customerDTO3.getName())))
                .andExpect(jsonPath("$[2].age", Matchers.is(customerDTO3.getAge())))
                .andExpect(jsonPath("$[2].createdAt", Matchers.notNullValue()));


    }

    @Test
    public void testGetCustomerById() throws Exception {

        Long id = 1L;

        CustomerDTO customerDTO1 = CustomerDTO.builder().id(1L).name("Test1").age("15").createdAt(new Date()).build();

        Mockito.when(customerService.getCustomerById(id)).thenReturn(customerDTO1);

        mockMvc.perform(get("/api/v1/customers/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(customerDTO1.getId().intValue())))
                .andExpect(jsonPath("$.name", Matchers.is(customerDTO1.getName())))
                .andExpect(jsonPath("$.age", Matchers.is(customerDTO1.getAge())))
                .andExpect(jsonPath("$.createdAt", Matchers.notNullValue()));


    }





} 
