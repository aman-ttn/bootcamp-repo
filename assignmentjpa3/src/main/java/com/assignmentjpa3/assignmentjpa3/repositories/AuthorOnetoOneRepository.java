package com.assignmentjpa3.assignmentjpa3.repositories;

import com.assignmentjpa3.assignmentjpa3.entities.onetoone.Authorone;
import org.springframework.data.repository.CrudRepository;

public interface AuthorOnetoOneRepository extends CrudRepository<Authorone,Integer> {
}
