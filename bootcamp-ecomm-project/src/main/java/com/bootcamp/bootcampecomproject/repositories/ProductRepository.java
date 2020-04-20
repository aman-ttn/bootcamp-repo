package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "select * from product where brand =:Brand and category_id =:CategoryId and seller_user_id =:SellerId and name =:Name",nativeQuery = true)
    public Product getUniqueProduct(@Param("Brand")String brand,@Param("CategoryId")Long categoryId,@Param("SellerId")Long sellerId,@Param("Name")String name);

    @Query(value = "select * from product where seller_user_id=:SellerId and is_deleted=false",nativeQuery = true)
    List<Product> getProductBySellerId(@Param("SellerId")Long id, Pageable pageable);

    List<Product> findByCategoryId(Long id,Pageable pageable);

    @Query(value = "select * from product where category_id=:CategoryId",nativeQuery = true)
    List<Product> getByCategoryId(@Param("CategoryId")Long id);
}
