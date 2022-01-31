package com.MRensen.transportApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes={TransportAppApplication.class})
class TransportAppApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertNotNull(context);
	}

}
