package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CategoryDao;
import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.ProductDao;
import com.bootcamp.bootcampecomproject.dao.SellerDao;
import com.bootcamp.bootcampecomproject.dtos.*;
import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategorySellerDto;
import com.bootcamp.bootcampecomproject.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
public class SellerController {
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;
    @GetMapping(value = "/seller/profile")
    public SellerProfileDto getProfile(HttpServletRequest httpServletRequest){
        return sellerDao.getProfile(httpServletRequest);
    }
    @PutMapping(value = "/seller/update")
    public String updateProfile(@RequestBody HashMap<String,Object> map, HttpServletRequest httpServletRequest){
        return sellerDao.updateProfile(map,httpServletRequest);
    }
    @PutMapping(value = "/seller/updatePassword")
    public String updatePassword(@RequestParam("password")String password, @RequestParam("confirmPassword") String confirmPassword, WebRequest webRequest, HttpServletRequest httpServletRequest){
        return customerDao.updatePassword(password,confirmPassword,webRequest,httpServletRequest);
    }
    @PutMapping(value = "/seller/updateAddress")
    public String updateAddress(@RequestParam("addressId")Long id,@RequestBody HashMap<String,Object> map,HttpServletRequest httpServletRequest){
        return sellerDao.updateAddress(map,id,httpServletRequest);
    }

    @GetMapping(value = "seller/getAllCategories")
    public List<CategorySellerDto> getAllCategories(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField,
            @RequestParam(defaultValue = "asc")String order
    ){
        return categoryDao.getCategoryForSeller(pageSize,pageOffset,sortByField,order);
    }
    @PostMapping(value = "/seller/addProduct")
    public String addpproduct(@RequestBody AddProductDto addProductDto,HttpServletRequest httpServletRequest,WebRequest webRequest){
        return productDao.addProduct(addProductDto,httpServletRequest,webRequest);
    }

    @PostMapping(value = "/seller/addProductVariation", consumes = { "multipart/form-data" })
    public String addProductVariation(@RequestPart("primaryImage") MultipartFile primaryImage, @RequestPart("secondaryImages")List<MultipartFile> secondaryImages,@RequestPart("addProductVariationDto") AddProductVariationDto addProductVariationDto, HttpServletRequest httpServletRequest, WebRequest webRequest) throws IOException {
        return productDao.addProductVariation(primaryImage,secondaryImages,addProductVariationDto,httpServletRequest,webRequest);
    }

    @GetMapping(value = "/seller/viewProduct")
    public ViewProductDto getProductByProductId(@RequestParam("ProductId")Long id,WebRequest webRequest,HttpServletRequest httpServletRequest){
        return productDao.viewProductbyId(id,webRequest,httpServletRequest);
    }

    @GetMapping(value = "/seller/viewProductVariation")
    public ViewProductVariationDto getProductVariationById(@RequestParam("ProductVariationId")Long id,WebRequest webRequest,HttpServletRequest httpServletRequest){
        return productDao.viewProductVariationbyId(id,webRequest,httpServletRequest);
    }

    @GetMapping(value = "/seller/viewAllProduct")
    public List<ViewProductDto> getAllProduct(
                                        @RequestParam(defaultValue = "10") String pageSize,
                                        @RequestParam(defaultValue = "0") String pageOffset,
                                        @RequestParam(defaultValue ="id")String sortByField,
                                        @RequestParam(defaultValue = "asc")String order,
                                        HttpServletRequest httpServletRequest,
                                        WebRequest webRequest){
        return productDao.getAllProduct(pageSize,pageOffset,sortByField,order,httpServletRequest,webRequest);
    }
    @GetMapping(value = "/seller/viewAllProductVariation")
    public List<ViewAllProductVariationDto> getAllProductVariation(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField,
            @RequestParam(defaultValue = "asc")String order,
            @RequestParam("Id") Long id,
            WebRequest webRequest){
        return productDao.getAllProductVariation(pageSize,pageOffset,sortByField,order,id,webRequest);
    }

    @DeleteMapping(value = "/seller/deleteProduct")
    public String deleteProduct(@RequestParam("ProductId")Long id,HttpServletRequest httpServletRequest,WebRequest webRequest){
        return productDao.deleteProduct(id,httpServletRequest,webRequest);
    }
    @PutMapping(value = "/seller/updateProduct")
    public String updateProduct(@RequestBody UpdateProductDto updateProductDto,HttpServletRequest httpServletRequest,WebRequest webRequest){
        return productDao.updateProduct(updateProductDto,httpServletRequest,webRequest);
    }
    @PutMapping(value = "/seller/updateProductVariation", consumes = { "multipart/form-data" })
    public String updateProductVariation(@RequestPart("primaryImage") MultipartFile primaryImage, @RequestPart("secondaryImages")List<MultipartFile> secondaryImages,@RequestPart("updateProductVariationDto") UpdateProductVariationDto updateProductVariationDto, HttpServletRequest httpServletRequest, WebRequest webRequest) throws IOException {
        return productDao.updateProductVariation(primaryImage,secondaryImages,updateProductVariationDto,httpServletRequest,webRequest);
    }


    @PostMapping(value = "/seller/uploadImageTest", consumes = { "multipart/form-data" })
    public String uploadImage(@NotNull @RequestPart("image") MultipartFile image, @RequestPart("images")List<MultipartFile> images, @RequestPart("dto") AddProductVariationDto addProductDto, HttpServletRequest httpServletRequest) throws IOException {
        System.out.println("Running========");
        if(!image.isEmpty())
        {
            return " Data get successfully with image "+images.size()+"-----"+addProductDto.getMetadata()+"-----";
        }
        else
            return " Data without image ";
    }
}
