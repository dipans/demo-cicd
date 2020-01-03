package com.examle.democicd.unittest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.examle.democicd.model.Person;
import com.examle.democicd.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(personRepository).isNotNull();
	}
	
	@Test
	public void testSavePerson_successfully() {
		Person person = Person.builder()
			.firstName("Dipan")
			.lastName("Sutradhar")
			.build();
		
		Person savedPerson = personRepository.save(person);
		assertThat(savedPerson.getId()).isNotZero();
		Optional<Person> readPerson = personRepository.findById(person.getId());
		assertThat(readPerson).isNotNull();
		assertThat(readPerson.isPresent()).isTrue();
		assertThat(readPerson.get().getId()).isEqualTo(savedPerson.getId());
		assertThat(readPerson.get().getFirstName()).isEqualTo(savedPerson.getFirstName());
		assertThat(readPerson.get().getLastName()).isEqualTo(savedPerson.getLastName());
		
	}
	
	@Test
	public void testFindAllPerson() {
		
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(Person.builder()
			.firstName("Dipan")
			.lastName("Sutradhar")
			.build());
		persons.add(Person.builder()
				.firstName("Kanchi")
				.lastName("Sutradhar")
				.build());
		
		List<Person> savedPersons = personRepository.saveAll(persons);
		assertThat(savedPersons.size()).isNotZero();
		/*Optional<Person> readPerson = personRepository.findById(person.getId());
		assertThat(readPerson).isNotNull();
		assertThat(readPerson.isPresent()).isTrue();
		assertThat(readPerson.get().getId()).isEqualTo(savedPerson.getId());
		assertThat(readPerson.get().getFirstName()).isEqualTo(savedPerson.getFirstName());
		assertThat(readPerson.get().getLastName()).isEqualTo(savedPerson.getLastName());*/
		
	}
	

}
