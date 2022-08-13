package org.hung.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Person {
	
	@Id
	private String Id;
	
	private String name;
	
	private String email;
	
}
