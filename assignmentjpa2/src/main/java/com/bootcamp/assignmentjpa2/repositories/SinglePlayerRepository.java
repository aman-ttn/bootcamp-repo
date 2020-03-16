package com.bootcamp.assignmentjpa2.repositories;

import com.bootcamp.assignmentjpa2.entities.singleTable.Playersingle;
import org.springframework.data.repository.CrudRepository;

public interface SinglePlayerRepository extends CrudRepository<Playersingle,Integer> {
}
