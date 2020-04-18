package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.CategoryDto;
import com.bootcamp.bootcampecomproject.dtos.CategoryMetadataFieldValuesDto;
import com.bootcamp.bootcampecomproject.dtos.OneCategoryDto;
import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategoryMetadataDto;
import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategorySellerDto;
import com.bootcamp.bootcampecomproject.entities.Category;
import com.bootcamp.bootcampecomproject.entities.CategoryMetadataField;
import com.bootcamp.bootcampecomproject.entities.CategoryMetadataFieldValue;
import com.bootcamp.bootcampecomproject.exception.CategoryException;
import com.bootcamp.bootcampecomproject.repositories.CategoryMetadataFieldRepository;
import com.bootcamp.bootcampecomproject.repositories.CategoryMetadataFieldValueRepository;
import com.bootcamp.bootcampecomproject.repositories.CategoryRepository;
import com.bootcamp.bootcampecomproject.utils.CategoryMetadataFieldValuesId;
import com.bootcamp.bootcampecomproject.utils.SetConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Component
public class CategoryDao {
    @Autowired
    private CategoryMetadataFieldRepository categoryMetadataFieldRepository;
    @Autowired
    private CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public String addMetadataField(String fieldName, WebRequest webRequest){
        if (fieldName==null || fieldName==""){
            throw new NullPointerException("Please enter the field name");
        }
        else {
            CategoryMetadataField categoryMetadataField=categoryMetadataFieldRepository.findByName(fieldName);
            if (categoryMetadataField!=null){
                return "Field Saved Successfully, and fieldId is :: "+categoryMetadataField.getId();
            }
            else {
                return "Field Saved Successfully, and fieldId is :: "+addNewMetadataField(fieldName);
            }
        }
    }

    private Long addNewMetadataField(String fieldName){
        CategoryMetadataField categoryMetadataField=new CategoryMetadataField();
        categoryMetadataField.setName(fieldName);
        categoryMetadataFieldRepository.save(categoryMetadataField);
        return categoryMetadataFieldRepository.findByName(fieldName).getId();
    }
    public List<CategoryMetadataField> getMetadatafield(String size, String offset, String field, String order){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);

        return categoryMetadataFieldRepository.findAll(pageable);
    }
    public String addCategory(String categoryName,Long parentId){
        if (checkNull(categoryName) && parentId!=null){
            if (categoryRepository.findBycategoryName(categoryName)!=null)
                throw new CategoryException("Category Already Exist exception");
            else if (categoryRepository.findByid(parentId)==null)
                throw new CategoryException("Parent does not exist");
            else {
                saveCategoryNameAndParentId(categoryName,parentId);
                return "Category Saved Successfully , and categoryId is :: "+categoryRepository.findBycategoryName(categoryName).getId();
            }
        }
        else if (checkNull(categoryName) && parentId==null){
            if (categoryRepository.findBycategoryName(categoryName)!=null)
                throw new CategoryException("Category Already Exist exception");
            else {
                saveCategoryName(categoryName);
                return "Category Saved Successfully , and categoryId is :: "+categoryRepository.findBycategoryName(categoryName).getId();
            }
        }
        else {
                throw new NullPointerException("Enter Category Name");
        }
    }
    private Boolean checkNull(String value){
        if(value!=null && !value.equals(""))
            return true;
        else
            return false;
    }
    private void saveCategoryNameAndParentId(String categoryName,Long parentId){
        Category category=new Category();
        category.setParentId(parentId);
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
    }
    private void saveCategoryName(String categoryName){
        Category category=new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
    }
    public OneCategoryDto getOneCategory(Long id){
        Category category=categoryRepository.findByid(id);
        List<String> parentCategoriesNull=new ArrayList<>();
        List<Category> childCategory=categoryRepository.findByParentId(category.getId());
        OneCategoryDto oneCategoryDto=new OneCategoryDto();
        oneCategoryDto.setId(id);
        oneCategoryDto.setCategoryName(category.getCategoryName());
        if (category.getParentId()!=null)
            oneCategoryDto.setParentCategories(getParentCategories(category.getParentId(),parentCategoriesNull));
        if (!childCategory.isEmpty())
            oneCategoryDto.setChildCategory(childCategory.get(0).getCategoryName());
        else
            oneCategoryDto.setChildCategory("Child category doesn't exist");
        return oneCategoryDto;
    }

    private List<String> getParentCategories(Long id, List<String> categories){
        Category category=categoryRepository.findByid(id);
        categories.add(category.getCategoryName());
        if (category.getParentId()!=null){
            return getParentCategories(category.getParentId(),categories);
        }
        else {
            return categories;
        }
    }
    public List<OneCategoryDto> getAllCategory(String size, String offset, String field,String order){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);
        List<OneCategoryDto> oneCategoryDtoList=new ArrayList<>();
        List<Category> allCategory= categoryRepository.findAll(pageable);
        allCategory.forEach(category -> {
            oneCategoryDtoList.add(getOneCategory(category.getId()));
        });
        return oneCategoryDtoList;
    }

    public String deleteCategoryById(java.lang.Long id){
        Category category=categoryRepository.findByid(id);
        if(category!=null){
            if (!categoryRepository.findByParentId(category.getId()).isEmpty()){
                throw new CategoryException("This is a parent Category,so you can't delete it");
            }
            else {
                categoryRepository.deleteById(id);
                return "Category deleted successfully";
            }
        }
        else {
            throw new CategoryException("This category doesn't exist");
        }
    }
    public String updateCategory(java.lang.Long id, String categoryName){
        Category category=categoryRepository.findByid(id);
        if (category!=null){
            if (categoryRepository.findBycategoryName(categoryName)==null){
                category.setCategoryName(categoryName);
                categoryRepository.save(category);
                return "Category Updated Successfully!";
            }
            else {
                throw new CategoryException("Category with this name is already exist!");
            }
        }
        else {
            throw new CategoryException("This category doesn't exist");
        }
    }
    public String addCategoryMetadataFieldValue(CategoryMetadataFieldValuesDto categoryMetadataFieldValuesDto){
        System.out.println(categoryMetadataFieldValuesDto.getCategoryId()+"   ------    "+categoryMetadataFieldValuesDto.getFieldId());
     Optional<Category> category=categoryRepository.findById(categoryMetadataFieldValuesDto.getCategoryId());
        Optional<CategoryMetadataField> categoryMetadataField=categoryMetadataFieldRepository.findById(categoryMetadataFieldValuesDto.getFieldId());
     if(category.isPresent()){
         if (categoryMetadataField.isPresent()){
             CategoryMetadataFieldValue categoryMetadataFieldValue=new CategoryMetadataFieldValue();
             CategoryMetadataFieldValuesId categoryMetadataFieldValuesId=new CategoryMetadataFieldValuesId();
             categoryMetadataFieldValuesId.setCategoryId(category.get().getId());
             categoryMetadataFieldValuesId.setCategoryMetadataFieldId(categoryMetadataField.get().getId());
             categoryMetadataFieldValue.setCategoryMetadataFieldValuesId(categoryMetadataFieldValuesId);

             categoryMetadataFieldValue.setCategory(category.get());
             categoryMetadataFieldValue.setCategoryMetadataField(categoryMetadataField.get());
             String values=SetConverter.convertToString(categoryMetadataFieldValuesDto.getFieldValues());
             categoryMetadataFieldValue.setValue(values);
             categoryMetadataFieldValueRepository.save(categoryMetadataFieldValue);
             return "Category Metadata field value saved successfully";
         }
         else
             throw new CategoryException("Enter valid CategoryMetadata field id");
     }
     else
         throw new CategoryException("Enter valid category id");
    }

    private Set<String> findIntersection(Set<String> set1, Set<String> set2){
        Set<String> intersection = new HashSet<String>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    private String validateAndReturnUpdatedFieldValues(Set<String> fieldValues, Long fieldId, Long categoryId){
        String value=categoryMetadataFieldValueRepository.findByCategoryAndMetadatafield(categoryId,fieldId).getValue();
        if (!fieldValues.isEmpty()) {
            Set<String> valueSet = SetConverter.convertToSet(value);
            Set<String> intersectionSet = findIntersection(fieldValues, valueSet);
            if (!intersectionSet.isEmpty()) {
                String duplicateElements = SetConverter.convertToString(intersectionSet);
                throw new CategoryException(duplicateElements + ":: These field values are already exist");
            }
            else {
                String updatedValues=value+","+SetConverter.convertToString(fieldValues);
                return updatedValues;

            }
        }
        else {
            throw new CategoryException("Enter at least one field values");
        }
    }

    public String updateCategoryMetadataValues(CategoryMetadataFieldValuesDto categoryMetadataFieldValuesDto){
        CategoryMetadataFieldValue categoryMetadataFieldValue=categoryMetadataFieldValueRepository.findByCategoryAndMetadatafield(categoryMetadataFieldValuesDto.getCategoryId(),categoryMetadataFieldValuesDto.getFieldId());
        if (categoryMetadataFieldValue!=null){
            String updatedValues=validateAndReturnUpdatedFieldValues(categoryMetadataFieldValuesDto.getFieldValues(),categoryMetadataFieldValuesDto.getFieldId(),categoryMetadataFieldValuesDto.getCategoryId());
            categoryMetadataFieldValue.setValue(updatedValues);
            categoryMetadataFieldValueRepository.save(categoryMetadataFieldValue);
            return "Category Metadata Field Value Updated Successfully";
        }
        else {
            throw new CategoryException("Enter valid and associated category and category metadata field id");
        }
    }
    public List<CategorySellerDto> getCategoryForSeller(String size, String offset, String field, String order){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);
        List<CategorySellerDto> categorySellerDtos=new ArrayList<>();
        List<Category> leafCategories=categoryRepository.getLeafCategory(pageable);
        leafCategories.forEach(category -> {
            CategorySellerDto categorySellerDto=new CategorySellerDto();
            categorySellerDto.setCategoryId(category.getId());
            categorySellerDto.setCategoryName(category.getCategoryName());
            categorySellerDto.setCategoryMetadataDtoList(getCategoryMetadata(category.getId()));
            Long parentId=category.getParentId();
            if (parentId!=null){
                List<String> parentCategories=new ArrayList<>();
                categorySellerDto.setParentCategories(getParentCategories(parentId,parentCategories));
            }
            categorySellerDtos.add(categorySellerDto);
        });
        return categorySellerDtos;
    }

    private List<CategoryMetadataDto> getCategoryMetadata(Long id) {
        List<CategoryMetadataDto> categoryMetadataDtoList=new ArrayList<>();
        List<Object[]> metadataList=categoryRepository.getMetadataByCategoryId(id);
        for (Object[] metadata:metadataList) {
            CategoryMetadataDto categoryMetadataDto=new CategoryMetadataDto((String) metadata[0],(String) metadata[1]);
            categoryMetadataDtoList.add(categoryMetadataDto);
        }
        return categoryMetadataDtoList;
    }

    public List<CategoryDto> getAllCategoriesCustomer(Long id){
        List<CategoryDto> allCategories=new ArrayList<>();
        if(id!=null){
            if (!categoryRepository.findById(id).isPresent())
                throw new CategoryException("Category with this id does not exist!");
            List<Category> childCategory=categoryRepository.findByParentId(id);
            CategoryDto categoryDto=new CategoryDto(childCategory.get(0).getId(),childCategory.get(0).getCategoryName());
            allCategories.add(categoryDto);
            return allCategories;

        }
        else{
            List<Category> rootParents=categoryRepository.getRootParent();
            rootParents.forEach(category -> {
                CategoryDto categoryDto=new CategoryDto(category.getId(),category.getCategoryName());
                allCategories.add(categoryDto);
            });
        }
        return allCategories;

    }




}
