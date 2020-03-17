package com.assignmentjpa3.assignmentjpa3.repositories;

import com.assignmentjpa3.assignmentjpa3.entities.onetomany.Authoronetomany;
import org.springframework.data.repository.CrudRepository;

public interface AuthorOnetoManyRepository extends CrudRepository<Authoronetomany,Integer> {
}
