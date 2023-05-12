package com.user.details.service;


import java.util.List;
import java.util.Optional;

import com.user.details.entities.User;
 
public interface Userservice {

	User saveUser(User user);
	
	List<User> getAlluser();
	
	User getUser(String userId);
}
