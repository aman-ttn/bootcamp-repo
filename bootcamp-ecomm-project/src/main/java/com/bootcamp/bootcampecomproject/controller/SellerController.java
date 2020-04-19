package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CategoryDao;
import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.ProductDao;
import com.bootcamp.bootcampecomproject.dao.SellerDao;
import com.bootcamp.bootcampecomproject.dtos.AddProductDto;
import com.bootcamp.bootcampecomproject.dtos.AddProductVariationDto;
import com.bootcamp.bootcampecomproject.dtos.SellerProfileDto;
import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategorySellerDto;
import com.bootcamp.bootcampecomproject.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
        System.out.println(addProductVariationDto.getProductId());
        return productDao.addProductVariation(primaryImage,secondaryImages,addProductVariationDto,httpServletRequest,webRequest);
    }



    @PostMapping(value = "/seller/uploadImageTest", consumes = { "multipart/form-data" })
    public String uploadImage(@RequestPart("image") MultipartFile image,@RequestPart("images")List<MultipartFile> images,@RequestPart("dto") AddProductVariationDto addProductDto, HttpServletRequest httpServletRequest) throws IOException {
        System.out.println("Running========");
        if(!image.isEmpty())
        {

            return " Data get successfully with image "+images.size()+"-----"+addProductDto.getMetadata()+"-----";
        }
        else
            return " Data without image ";
    }
}
