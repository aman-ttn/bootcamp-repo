package com.bootcamp.assignmentjpa2.repositories;

import com.bootcamp.assignmentjpa2.entities.joined.Playerjoined;
import org.springframework.data.repository.CrudRepository;

public interface JoinedPlayerRepository extends CrudRepository<Playerjoined,Integer> {
}
