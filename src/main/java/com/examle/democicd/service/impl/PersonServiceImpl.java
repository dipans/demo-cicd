package com.examle.democicd.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examle.democicd.exception.PersonNotFoundException;
import com.examle.democicd.model.Person;
import com.examle.democicd.repository.PersonRepository;
import com.examle.democicd.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	
	/*public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}*/

	@Override
	public Person getPersonById(long id) throws PersonNotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Unknown person for id - " + id));
	}

	@Override
	public Person savePerson(Person person) {
		return personRepository.saveAndFlush(person);
	}

	@Override
	public List<Person> listAllPerson() {
		return personRepository.findAll();
	}

}
