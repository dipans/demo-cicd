package com.examle.democicd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.examle.democicd.resource.PersonResource;

/**
 * 
 * This is a smoke test test the load of the application.
 * 
 * @author Dipan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoCicdApplicationTests {

	@Autowired
	private PersonResource personResource;

	@Test
	public void contextLoads() {
		assertThat(personResource).isNotNull();
	}

}
