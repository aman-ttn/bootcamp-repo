package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.AddProductDto;
import com.bootcamp.bootcampecomproject.entities.Category;
import com.bootcamp.bootcampecomproject.entities.Product;
import com.bootcamp.bootcampecomproject.entities.Seller;
import com.bootcamp.bootcampecomproject.exception.CategoryException;
import com.bootcamp.bootcampecomproject.exception.ProductException;
import com.bootcamp.bootcampecomproject.repositories.CategoryRepository;
import com.bootcamp.bootcampecomproject.repositories.ProductRepository;
import com.bootcamp.bootcampecomproject.repositories.SellerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class ProductDao {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public String addProduct(AddProductDto addProductDto, HttpServletRequest httpServletRequest, WebRequest webRequest){
        String email=getEmailbyToken(httpServletRequest);
        Long userId=userRepository.findByEmail(email).getId();
        Seller seller=sellerRepository.findByUserId(userId);
        Category category=categoryRepository.findById(addProductDto.getCategoryId()).get();
        Product product=new Product();
        product.setSeller(seller);
        product.setBrand(addProductDto.getBrand());
        Boolean productUnique=isProductUnique(addProductDto.getName(),addProductDto.getCategoryId(),seller.getUser().getId(),addProductDto.getBrand());
        if (productUnique)
            product.setName(addProductDto.getName());
        else
            throw new ProductException("Product with this name is already exist.");

        if (categoryRepository.findByParentId(addProductDto.getCategoryId()).isEmpty() && category!=null)
            product.setCategory(category);
        else
            throw new CategoryException("Invalid category id");
        if (addProductDto.getDescription()!=null)
            product.setDescription(addProductDto.getDescription());
        if (addProductDto.getCancellable()!=null)product.setSeller(seller);
        product.setBrand(addProductDto.getBrand());
            product.setCancellable(addProductDto.getCancellable());
        if (addProductDto.getReturnable()!=null)
            product.setReturnable(addProductDto.getReturnable());
        productRepository.save(product);
        return "Product Saved Successfully";
    }

    private Boolean isProductUnique(String name,Long categoryId, Long sellerId,String brand) {
        Product product=productRepository.getUniqueProduct(brand,categoryId,sellerId,name);
        if(product==null)
            return true;
        else
            return false;
    }

    private String getEmailbyToken(HttpServletRequest httpServletRequest){
        String email=httpServletRequest.getUserPrincipal().getName();
        return email;
    }
}
