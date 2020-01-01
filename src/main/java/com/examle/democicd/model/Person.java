package com.examle.democicd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Person {
	
	@Id
	@GeneratedValue
	private long id;
	@Size(min = 3, max = 20)
	private String firstName;
	@Size(min = 3, max = 20)
	private String lastName;

}
