package com.springsecurityoauth2jpa.springsecurityoauth2jpaassignment.repositories;

import com.springsecurityoauth2jpa.springsecurityoauth2jpaassignment.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);
}
