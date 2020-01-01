package com.examle.democicd.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.examle.democicd.exception.PersonNotFoundException;
import com.examle.democicd.model.Person;
import com.examle.democicd.service.PersonService;

@RestController
public class PersonResource {

	private PersonService personService;

	@Autowired
	public PersonResource(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping(path = "/person")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Person> getAllPerson() {
		return personService.listAllPerson();
	}

	@GetMapping(path = "/person/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Person getPerson(@PathVariable long id) {
		return personService.getPersonById(id);
	}

	@PostMapping(path = "/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Person addPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}

}
