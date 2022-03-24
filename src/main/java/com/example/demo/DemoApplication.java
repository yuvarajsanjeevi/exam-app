package com.example.demo;

import com.example.demo.source.entity.Person;
import com.example.demo.source.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		//Use this for Prod
//		SpringApplication.run(DemoApplication.class, args);

		//Use this for Test
				ConfigurableApplicationContext configurableApplicationContext =
						SpringApplication.run(DemoApplication.class, args);
				PersonRepository personRepository =
						configurableApplicationContext.getBean(PersonRepository.class);
				Person myPerson = new Person("John", "Doe");
				personRepository.save(myPerson);
	}

}
