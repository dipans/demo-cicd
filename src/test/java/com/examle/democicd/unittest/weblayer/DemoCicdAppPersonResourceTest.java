package com.examle.democicd.unittest.weblayer;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.examle.democicd.exception.PersonNotFoundException;
import com.examle.democicd.model.Person;
import com.examle.democicd.service.PersonService;

@RunWith(SpringRunner.class)
//@SpringBootTest()
//@AutoConfigureMockMvc()
@WebMvcTest
public class DemoCicdAppPersonResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void shouldReturnPerson_WhenCreatedSucesfully() throws Exception {
		
		
		Person person = Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build();
		
		given(personService.savePerson(Mockito.any(Person.class))).willReturn(person);
		
		this.mockMvc.perform(post("/person").content("{\"id\":\"1\",\"firstName\":\"Dipan\",\"lastName\":\"Sutradhar\"}")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8"))
					//.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.id", is(1)))
					.andExpect(jsonPath("$.firstName", is("Dipan")))
					.andExpect(jsonPath("$.lastName", is("Sutradhar")))
					.andReturn();
	}

	@Test
	public void shouldReturnAllPerson() throws Exception {

		List<Person> persons = new ArrayList<Person>();
		persons.add(Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build());
		given(personService.listAllPerson()).willReturn(persons);
		
		this.mockMvc.perform(get("/person").content("[{\"id\": 1,\"firstName\":\"Dipan\",\"lastName\":\"Sutradhar\"}]"))
					//.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1)))
					.andExpect(jsonPath("$[0].id", is(1)))
					.andExpect(jsonPath("$[0].firstName", is("Dipan")))
					.andExpect(jsonPath("$[0].lastName", is("Sutradhar")));
	}
	
	@Test
	public void shouldReturnPerson_WhenPersonAvailable() throws Exception {
		
		
		Person person = Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build();
		
		given(personService.getPersonById(Mockito.any(Long.class))).willReturn(person);
		
		this.mockMvc.perform(get("/person/1"))
					//.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is(1)))
					.andExpect(jsonPath("$.firstName", is("Dipan")))
					.andExpect(jsonPath("$.lastName", is("Sutradhar")))
					.andReturn();
	}
	
	@Test
	public void shouldThrow_WhenPersonNotAvailable() throws Exception {
		
		given(personService.getPersonById(Mockito.any(Long.class))).willThrow(PersonNotFoundException.class);
		
		this.mockMvc.perform(get("/person/1"))
					//.andDo(print())
					.andExpect(status().isNotFound())
					.andReturn();
	}

}
