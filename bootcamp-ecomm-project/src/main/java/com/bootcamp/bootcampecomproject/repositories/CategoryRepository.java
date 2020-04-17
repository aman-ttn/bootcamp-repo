package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category findBycategoryName(String name);
    Category findByid(Long id);
    List<Category> findByParentId(Long id);

    List<Category> findAll(Pageable pageable);
}
