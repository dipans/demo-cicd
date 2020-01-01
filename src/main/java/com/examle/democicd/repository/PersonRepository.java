package com.examle.democicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examle.democicd.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
