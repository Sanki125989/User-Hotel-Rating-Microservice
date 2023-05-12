package com.user.details.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.details.entities.User;

@Repository
public interface Userrepo extends JpaRepository<User, String> {

}
