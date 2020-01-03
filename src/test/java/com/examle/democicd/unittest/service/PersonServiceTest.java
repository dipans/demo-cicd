package com.examle.democicd.unittest.service;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.examle.democicd.exception.PersonNotFoundException;
import com.examle.democicd.model.Person;
import com.examle.democicd.repository.PersonRepository;
import com.examle.democicd.service.impl.PersonServiceImpl;
import static org.mockito.BDDMockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	@InjectMocks
	private PersonServiceImpl personSerice;

	@Mock
	private PersonRepository personRepository;

	@org.junit.Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void whenPersonAvailable_returnAll() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build());
		persons.add(Person.builder().id(2l).firstName("Kanchi").lastName("Sutradhar").build());
		when(personRepository.findAll()).thenReturn(persons);

		List<Person> resultPersons = personSerice.listAllPerson();
		Assertions.assertThat(resultPersons).asList();
		Assertions.assertThat(resultPersons).isNotEmpty();
		Assertions.assertThat(resultPersons).contains(persons.get(0));
		Assertions.assertThat(resultPersons).contains(persons.get(1));

	}

	@Test
	public void whenPersonAvailable_returnById() {
		Person person = Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build();
		when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(person));

		Person resultPerson = personSerice.getPersonById(1L);
		Assertions.assertThat(resultPerson).isNotNull();
		Assertions.assertThat(resultPerson.getFirstName()).isEqualTo("Dipan");
		Assertions.assertThat(resultPerson.getLastName()).isEqualTo("Sutradhar");

	}

	@Test
	public void whenPersonNoavailable_returnById() {

		when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		expectedException.expect(PersonNotFoundException.class);
		expectedException.expectMessage("Unknown person for id - 1");
		personSerice.getPersonById(1L);

	}
	
	@Test
	public void whenPersonSaved_returnPerson() {
		Person person = Person.builder().id(1l).firstName("Dipan").lastName("Sutradhar").build();
		when(personRepository.saveAndFlush(person)).thenReturn(person);
		Person resultPerson = personSerice.savePerson(person);
		Assertions.assertThat(resultPerson).isNotNull();
		Assertions.assertThat(resultPerson.getFirstName()).isEqualTo("Dipan");
		Assertions.assertThat(resultPerson.getLastName()).isEqualTo("Sutradhar");

	}

}
