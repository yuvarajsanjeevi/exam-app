package com.example.demo.source.controller.v1;

import com.example.demo.source.dto.PersonDTO;
import com.example.demo.source.dto.SayHelloResult;
import com.example.demo.source.dto.TranslateResult;
import com.example.demo.source.entity.Person;
import com.example.demo.source.service.MessageService;
import com.example.demo.source.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    @MockBean
    private PersonService personService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllPersons() throws Exception {
        PersonDTO personDTO1 = PersonDTO.builder().id(1L).firstName("Test1").lastName("Test1Last").build();
        PersonDTO personDTO2 = PersonDTO.builder().id(2L).firstName("Test2").lastName("Test2Last").build();
        PersonDTO personDTO3 = PersonDTO.builder().id(3L).firstName("Test3").lastName("Test3Last").build();

        List<PersonDTO> personList = Arrays.asList(personDTO1, personDTO2, personDTO3);

        Mockito.when(personService.getAllPersons()).thenReturn(personList);

        mockMvc.perform(get("/api/v1/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].id", Matchers.is(personDTO1.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", Matchers.is(personDTO1.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", Matchers.is(personDTO1.getLastName())))

                .andExpect(jsonPath("$[1].id", Matchers.equalTo(personDTO2.getId().intValue())))
                .andExpect(jsonPath("$[1].firstName", Matchers.is(personDTO2.getFirstName())))
                .andExpect(jsonPath("$[1].lastName", Matchers.is(personDTO2.getLastName())))

                .andExpect(jsonPath("$[2].id", Matchers.equalTo(personDTO3.getId().intValue())))
                .andExpect(jsonPath("$[2].firstName", Matchers.is(personDTO3.getFirstName())))
                .andExpect(jsonPath("$[2].lastName", Matchers.is(personDTO3.getLastName())));


    }


} 
