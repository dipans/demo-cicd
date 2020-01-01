package com.examle.democicd.unittest.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.examle.democicd.model.Person;
import com.examle.democicd.repository.PersonRepository;
import com.examle.democicd.service.PersonService;
import static org.mockito.BDDMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personSerice;
	
	@Mock
	private PersonRepository personRepository;// = Mockito.mock(PersonRepository.class);
	
	//@Before
	//public void init() {
	//	MockitoAnnotations.initMocks(personRepository);
	//}
	
	@Test
	public void whenPersonAvailable_returnAll() {
		 //PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		List<Person> persons = new ArrayList<Person>();
		persons.add(Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build());
		persons.add(Person.builder().id(2l).firstName("Kanchi").lastName("Sutradhar").build());
		
		System.out.println(personRepository);
		when(personRepository.findAll()).thenReturn(persons);
		
		List<Person> resultPersons = personSerice.listAllPerson();
		Assertions.assertThat(resultPersons).asList();
		Assertions.assertThat(resultPersons).isNotEmpty();
		Assertions.assertThat(resultPersons).contains(persons.get(0));
		Assertions.assertThat(resultPersons).contains(persons.get(1));
		
	}

}
