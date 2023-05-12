package com.user.details.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_micro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	private String userId;
	private String username;
	private String email;
	private String about;
	
	@Transient
	private ArrayList rating=new ArrayList();	
}
