package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.CategoryMetadataFieldValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetadataFieldValueRepository extends CrudRepository<CategoryMetadataFieldValue,Long> {

    @Query(value = "select * from category_metadata_field_value where category_id=:CategoryId and category_metadata_field_id=:CategoryMetadataFieldId",nativeQuery = true)
    public CategoryMetadataFieldValue findByCategoryAndMetadatafield(@Param("CategoryId")Long categoryId,@Param("CategoryMetadataFieldId")Long categoryMetadataFieldId);

    @Query(value = "select value from category_metadata_field_value where category_id =:CategoryId and category_metadata_field_id =:FieldId",nativeQuery = true)
    public List<Object> getMetadataFieldValuesByCategoryAndFieldId(@Param("CategoryId")Long categoryId,@Param("FieldId")Long fieldId);
}
