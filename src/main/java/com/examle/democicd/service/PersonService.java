package com.examle.democicd.service;

import java.util.List;
import com.examle.democicd.model.Person;

public interface PersonService {
	
	public Person getPersonById(long id);

	public Person savePerson(Person person);
	
	public List<Person> listAllPerson();

}
