package com.bootcamp.assignmentjpa2.repositories;

import com.bootcamp.assignmentjpa2.entities.tablePerClass.PlayerTable;
import org.springframework.data.repository.CrudRepository;

public interface TablePlayerRepositry extends CrudRepository<PlayerTable,Integer> {
}
