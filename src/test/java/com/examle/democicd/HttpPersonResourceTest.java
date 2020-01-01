package com.examle.democicd;

import static org.assertj.core.api.Assertions.assertThat;

import org.h2.security.auth.H2AuthConfig;
import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.examle.democicd.model.Person;
import com.examle.democicd.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoCicdApplication.class,
		H2AuthConfig.class },  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HttpPersonResourceTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PersonRepository personRepository;
	
	
	public void addPerson_ShoudldCreatePerson() {
		Person person = new Person();
		person.setFirstName("Dipan");
		person.setLastName("Sutradhar");	
		String url = "http://localhost:" + port + "/person";	
		Person createdPerson = restTemplate.postForObject(url, person, Person.class);
		assertThat(createdPerson.getId()).isNotZero();
		assertThat(createdPerson.getFirstName()).isEqualTo(person.getFirstName());
		assertThat(createdPerson.getLastName()).isEqualTo(person.getLastName());
	}
	

	@Test
	@Order(1)
	public void getPersonsShouldReturnAllPerson() throws JSONException {
		addPerson_ShoudldCreatePerson();
		String uri = "http://localhost:" + port + "/person";
		String response = restTemplate.getForObject(uri, String.class);
		JSONAssert.assertEquals("[{\"id\": 1,\"firstName\": \"Dipan\",\"lastName\": \"Sutradhar\"}]", response, false);
	}
	
	@Test
	@Order(2)
	public void getPersonShouldReturnDesiredPerson() throws JSONException {
		Person person = restTemplate.getForObject("http://localhost:" + port + "/person/{id}", Person.class, "1");
		assertThat(person).isNotNull();
		assertThat(person.getId()).isEqualTo(1);
		assertThat(person.getFirstName()).isEqualTo("Dipan");
		assertThat(person.getLastName()).isEqualTo("Sutradhar");
	}

}
