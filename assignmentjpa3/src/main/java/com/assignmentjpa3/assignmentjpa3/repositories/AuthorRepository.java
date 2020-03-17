package com.assignmentjpa3.assignmentjpa3.repositories;

import com.assignmentjpa3.assignmentjpa3.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Integer> {
}
