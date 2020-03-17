package com.assignmentjpa3.assignmentjpa3.repositories;

import com.assignmentjpa3.assignmentjpa3.entities.manytomany.Authormanytomany;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AuthorManytoManyRepository extends CrudRepository<Authormanytomany,Integer> {

}
