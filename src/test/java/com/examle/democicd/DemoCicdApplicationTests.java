package com.examle.democicd;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.examle.democicd.resource.PersonResource;

/**
 * 
 * This is a smoke test test the load of the application.
 * 
 * @author Dipan
 *
 */
@SpringBootTest
class DemoCicdApplicationTests {

	@Autowired
	private PersonResource personResource;

	@Test
	void contextLoads() {
		assertThat(personResource).isNotNull();
	}

}
