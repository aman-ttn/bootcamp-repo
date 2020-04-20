package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.ProductVariation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {
    List<ProductVariation> findByProductId(Long id);
}
