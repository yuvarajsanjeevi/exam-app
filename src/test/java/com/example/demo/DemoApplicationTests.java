package com.example.demo;


import com.example.demo.source.controller.v1.CustomerController;
import com.example.demo.source.controller.v1.MessageController;
import com.example.demo.source.controller.v1.PersonController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	CustomerController customerController;

	@Autowired
	MessageController messageController;

	@Autowired
	PersonController personController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(Arrays.asList(customerController, messageController, personController)).allMatch(Objects::nonNull);
	}
}
