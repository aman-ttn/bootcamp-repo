package com.bootcamp.assignmentjpa2.repositories;

import com.bootcamp.assignmentjpa2.entities.componentMapping.Employeemapping;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeMappingRepository extends CrudRepository<Employeemapping,Integer> {
}
