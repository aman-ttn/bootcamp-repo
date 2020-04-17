package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.AdminDao;
import com.bootcamp.bootcampecomproject.dao.CategoryDao;
import com.bootcamp.bootcampecomproject.dtos.CategoryMetadataFieldValuesDto;
import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.dtos.FindAllSellerDto;
import com.bootcamp.bootcampecomproject.dtos.OneCategoryDto;
import com.bootcamp.bootcampecomproject.entities.CategoryMetadataField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private CategoryDao categoryDao;
    @GetMapping("/customers")
    public List<FindAllCustomerDto> getAllCustomers(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField
    ){
        return adminDao.getAllCustomer(pageSize,pageOffset,sortByField);
    }
    @GetMapping("/sellers")
    public List<FindAllSellerDto> getAllSeller(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField
    ){
        return adminDao.getAllSeller(pageSize,pageOffset,sortByField);
    }
    @PutMapping("/activate/customer")
    public String activateCustomer(@RequestParam("UserId") Long id, WebRequest webRequest){
        return adminDao.activateCustomer(id,webRequest);
    }
    @PutMapping("/deactivate/customer")
    public String deactivateCustomer(@RequestParam("UserId") Long id, WebRequest webRequest){
        return adminDao.deactivateCustomer(id,webRequest);
    }
    @PutMapping("/activate/seller")
    public String activateSeller(@RequestParam("UserId") Long id, WebRequest webRequest){
        return adminDao.activateSeller(id,webRequest);
    }
    @PutMapping("/deactivate/seller")
    public String deactivateSeller(@RequestParam("UserId") Long id, WebRequest webRequest){
        return adminDao.deactivateSeller(id,webRequest);
    }
    @PostMapping(value = "/addMetadatafield")
    public String addMetadataField(@RequestParam("FieldName")String fieldName,WebRequest webRequest){
        return categoryDao.addMetadataField(fieldName,webRequest);
    }
    @GetMapping(value = "/getMetadatafield")
    public List<CategoryMetadataField> getMetadataField(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField,
            @RequestParam(defaultValue = "asc")String order
    ){
        return categoryDao.getMetadatafield(pageSize,pageOffset,sortByField,order);
    }
    @PostMapping(value = "/addCategory")
    public String addCategory(@RequestParam("CategoryName") String categoryName,@RequestParam(defaultValue = "") Long parentId){
        return categoryDao.addCategory(categoryName,parentId);
    }
    @GetMapping(value = "/getCategory")
    public OneCategoryDto getCategory(@RequestParam("CategoryId") Long id){
        return categoryDao.getOneCategory(id);
    }

    @GetMapping(value = "/getAllCategories")
    public List<OneCategoryDto> getAllCategories(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField,
            @RequestParam(defaultValue = "asc")String order
    ){
        return categoryDao.getAllCategory(pageSize,pageOffset,sortByField,order);
    }
    @DeleteMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam("Id") Long id){
        return categoryDao.deleteCategoryById(id);
    }
    @PutMapping(value = "/updateCategory")
    public String updateCategory(@RequestParam("Id") Long id, @RequestParam("CategoryName")String categoryName){
        return categoryDao.updateCategory(id,categoryName);
    }
    @PostMapping(value = "/addCategoryMetadataFieldValue")
    public String addCategoryMetadataFieldValue(@RequestBody CategoryMetadataFieldValuesDto categoryMetadataFieldValuesDto){
        return categoryDao.addCategoryMetadataFieldValue(categoryMetadataFieldValuesDto);
    }
    @PutMapping(value = "/updateCategoryMetadataFieldValue")
    public String updateCategoryMetadataFieldValue(@RequestBody CategoryMetadataFieldValuesDto categoryMetadataFieldValuesDto){
        return categoryDao.updateCategoryMetadataValues(categoryMetadataFieldValuesDto);
    }
}
