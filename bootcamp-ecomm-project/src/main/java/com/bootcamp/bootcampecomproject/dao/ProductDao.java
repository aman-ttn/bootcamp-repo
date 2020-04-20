package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.*;
import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategoryMetadataDto;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.CategoryException;
import com.bootcamp.bootcampecomproject.exception.CustomException;
import com.bootcamp.bootcampecomproject.exception.ProductException;
import com.bootcamp.bootcampecomproject.repositories.*;
import com.bootcamp.bootcampecomproject.utils.SetConverter;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    private CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    private UploadImageDao uploadImageDao;

    @Autowired
    private ProductVariationRepository productVariationRepository;

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
        Long productId=productRepository.getUniqueProduct(product.getBrand(),product.getCategory().getId(),product.getSeller().getId(),product.getName()).getId();
        List<User> userAdmin=userRepository.getUserAdmin();
        String confirmationUrl = webRequest.getContextPath() + "/admin/activateProduct?id=" + productId;
        userAdmin.forEach(user -> {
            sendMailToAdmin(user.getEmail(),confirmationUrl,product);
        });

        return "Product Saved Successfully";
    }
    private void sendMailToAdmin(String emailRecipient,String confirmationUrl,Product product){
        String message=getMailBody(product);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailRecipient);
        simpleMailMessage.setSubject("Product Activation Confirmation");
        simpleMailMessage.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(simpleMailMessage);
    }
    private String getMailBody(Product product){
        String mailBody=
                    "Seller Name = " + product.getSeller().getCompanyName() +"\r\n"+
                    "Product Name = " + product.getName() + "\r\n" +
                    "Description = " + product.getDescription() + "\r\n" +
                    "IsCancellable = " + product.getCancellable() + "\r\n"+
                    "IsReturnable = " + product.getReturnable() + "\r\n"+
                    "Brand = " + product.getBrand() + "\r\n" +
                    "IsActive = " + product.getActive() + "\r\n"+
                    "Category = " + product.getCategory().getCategoryName() ;
                    return mailBody;
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
    public String activateProduct(Long productId, WebRequest webRequest){
        String message;
        Locale locale=webRequest.getLocale();
        Product product=productRepository.findById(productId).get();
        String sellerEmail=product.getSeller().getUser().getEmail();
        if (product.getActive()==false){
            product.setActive(true);
            message=messageSource.getMessage("productActivation.successful",null,locale);
        }
        else {
            message=messageSource.getMessage("productActivation.already.successful",null,locale);
        }
        productRepository.save(product);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        String subject=message;
        String mailMessage=messageSource.getMessage("sellerMailMessage.productActivation",null,locale)+product.getName()+
                messageSource.getMessage("sellerMailMessage.productActivationSuccessful",null,locale);
        simpleMailMessage.setTo(sellerEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(mailMessage);
        javaMailSender.send(simpleMailMessage);
        return message;
    }
    public String deactivateProduct(Long productId, WebRequest webRequest){
        String message;
        Locale locale=webRequest.getLocale();
        Product product=productRepository.findById(productId).get();
        String sellerEmail=product.getSeller().getUser().getEmail();
        if (product.getActive()==true){
            product.setActive(false);
            message=messageSource.getMessage("productDeActivation.successful",null,locale);
        }
        else {
            message=messageSource.getMessage("productDeactivated.already",null,locale);
        }
        productRepository.save(product);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        String subject=message;
        String mailMessage=messageSource.getMessage("sellerMailMessage.productDeActivation",null,locale)+product.getName()+
                messageSource.getMessage("sellerMailMessage.productDeActivationSuccessful",null,locale);
        simpleMailMessage.setTo(sellerEmail);
        simpleMailMessage.setSubject(subject);
        String mailBody=mailMessage+"\r\n"+getMailBody(product);
        simpleMailMessage.setText(mailBody);
        javaMailSender.send(simpleMailMessage);
        return message;
    }
    public String addProductVariation(MultipartFile primaryImage, List<MultipartFile> secondaryImages, AddProductVariationDto addProductVariationDto,HttpServletRequest httpServletRequest,WebRequest webRequest) throws IOException {
        String sellerEmail=httpServletRequest.getUserPrincipal().getName();
        String result=validateMetadata(addProductVariationDto,sellerEmail);
        if (result.equalsIgnoreCase("Success")){
            ProductVariation productVariation=new ProductVariation();
            productVariation.setProduct(productRepository.findById(addProductVariationDto.getProductId()).get());
            productVariation.setPrice(addProductVariationDto.getPrice());
            productVariation.setQuantityAvailable(addProductVariationDto.getQuantityAvailable());
            String primaryImagePath=uploadImageDao.uploadProductVariationPrimaryImage(primaryImage,addProductVariationDto.getProductId());
            productVariation.setPrimaryImageName(primaryImagePath);
            productVariation.setProductAttributes(addProductVariationDto.getMetadata());
            if(!secondaryImages.isEmpty()){
                Set<String> secondaryImagesPath=uploadImageDao.uploadProductVariationSecondaryImages(secondaryImages,addProductVariationDto.getProductId());
                String imagesPath=SetConverter.convertToString(secondaryImagesPath);
                productVariation.setSecondaryImageName(imagesPath);
            }
            productVariationRepository.save(productVariation);
            return "Product Variation Saved Successfully!";
        }
        else {
            throw new CustomException(result);
        }
    }
    private String validateMetadata(AddProductVariationDto addProductVariationDto,String sellerEmail){
        String message;
        Optional<Product> productOptional=productRepository.findById(addProductVariationDto.getProductId());
        if (!productOptional.isPresent()){
            message="Product Id is invalid";
            return message;
        }
        Product product=productOptional.get();

        Integer quantity=addProductVariationDto.getQuantityAvailable();
        if (!product.getSeller().getUser().getEmail().equalsIgnoreCase(sellerEmail)){
            message="This is not your product";
            return message;
        }
        if (quantity!=null){
            if (quantity<0){
                message="Product quantity must not be negative";
                return message;
            }
        }
        Double price=addProductVariationDto.getPrice();
        if (price<0){
            message="Product price must not be negative";
            return message;
        }
        if (product.getDeleted()){
            message="Product is deleted";
            return message;
        }
        if (!product.getActive()){
            message="Product is inactive";
            return message;
        }
        Category category=product.getCategory();
        Map<String,String> metadataMap=addProductVariationDto.getMetadata();

        List<String> mapFields=new ArrayList<>(metadataMap.keySet());
        List<Object> metadataField=categoryRepository.getMetadataFieldsNameByCategoryId(category.getId());
        List<String> actualFields=new ArrayList<>();
        metadataField.forEach(field->{
            actualFields.add((String) field);
        });
        System.out.println(mapFields.size()+".........."+actualFields.size());
        if(mapFields.size()<actualFields.size()){
            message="All category of metadata fields are not provided.";
            return message;
        }
        System.out.println(mapFields+".............");
        mapFields.removeAll(actualFields);
        if(!mapFields.isEmpty()){
            message="Fields are not associated with category";
            return message;
        }
        List<String> recievedField=new ArrayList<>(metadataMap.keySet());
        for (String field:recievedField) {
            CategoryMetadataField categoryMetadataField=categoryMetadataFieldRepository.findByName(field);
            List<Object>  objectList=categoryMetadataFieldValueRepository.getMetadataFieldValuesByCategoryAndFieldId(category.getId(),categoryMetadataField.getId());
            String values=objectList.get(0).toString();
            Set<String> actualValues= SetConverter.convertToSet(values);
            String recievedValue=metadataMap.get(field);
            if (recievedValue.isEmpty()){
                message="Enter at least one value for field "+field;
                return message;
            }
            Set<String> recievedValues=SetConverter.convertToSet(recievedValue);
            System.out.println(recievedValue+"-------"+field);
            Set<String> union=Sets.union(recievedValues,actualValues);
            if (union.size()>actualFields.size()){
                message="Field value is Invalid for "+field;
                return message;
            }
        }
        return "Success";
    }
    public ViewProductDto viewProductbyId(Long productId,WebRequest webRequest,HttpServletRequest httpServletRequest){
        String requestSellerEmail=httpServletRequest.getUserPrincipal().getName();
        Optional<Product> productOptional=productRepository.findById(productId);

        if(!productOptional.isPresent()){
            String message="Product Id is Invalid.";
            throw new CustomException(message);
        }
        Product product=productOptional.get();
        String actualSellerEmail=product.getSeller().getUser().getEmail();
        if (!requestSellerEmail.equals(actualSellerEmail)){
            String message="This product does not belongs to you";
            throw new CustomException(message);
        }
        if (product.getDeleted()){
            String message="This product is deleted.";
            throw new CustomException(message);
        }
        else {
            return setViewProductDto(product);
        }

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
    public ViewProductVariationDto viewProductVariationbyId(Long productVariationId, WebRequest webRequest, HttpServletRequest httpServletRequest) {
        String requestSellerEmail = httpServletRequest.getUserPrincipal().getName();
        Optional<ProductVariation> productVariationOptional = productVariationRepository.findById(productVariationId);

        if (!productVariationOptional.isPresent()) {
            String message = "Product Variation Id is Invalid.";
            throw new CustomException(message);
        }
        ProductVariation productVariation = productVariationOptional.get();
        Product product = productRepository.findById(productVariation.getProduct().getId()).get();
        String actualSellerEmail = product.getSeller().getUser().getEmail();
        if (!requestSellerEmail.equals(actualSellerEmail)) {
            String message = "This product variation does not belongs to you";
            throw new CustomException(message);
        }
        if (product.getDeleted()) {
            String message = "This product is deleted.";
            throw new CustomException(message);
        } else {
            return setProductVariationDto(product,productVariation);
        }
    }
    private ViewProductVariationDto setProductVariationDto(Product product,ProductVariation productVariation){
        ViewProductVariationDto viewProductVariationDto = new ViewProductVariationDto();
        viewProductVariationDto.setProductId(product.getId());
        viewProductVariationDto.setProductName(product.getName());
        viewProductVariationDto.setProductBrand(product.getBrand());
        viewProductVariationDto.setProductDescription(product.getDescription());
        viewProductVariationDto.setVariationPrice(productVariation.getPrice());
        viewProductVariationDto.setVariationQuantityAvailable(productVariation.getQuantityAvailable());
        viewProductVariationDto.setVariationActive(productVariation.getActive());
        viewProductVariationDto.setPrimaryImageName(productVariation.getPrimaryImageName());
        if (productVariation.getSecondaryImageName()!=null)
            viewProductVariationDto.setSecondaryImageName(SetConverter.convertToSet(productVariation.getSecondaryImageName()));
        viewProductVariationDto.setMetadata(productVariation.getProductAttributes());
        viewProductVariationDto.setCancellable(product.getCancellable());
        viewProductVariationDto.setReturnable(product.getReturnable());
        viewProductVariationDto.setVariationActive(productVariation.getActive());
        return viewProductVariationDto;
    }
    public List<ViewProductDto> getAllProduct(String size, String offset, String field, String order,HttpServletRequest httpServletRequest,WebRequest webRequest){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable= PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);
        String sellerEmail=httpServletRequest.getUserPrincipal().getName();
        Long sellerId=userRepository.findByEmail(sellerEmail).getId();
        List<Product> products=productRepository.getProductBySellerId(sellerId,pageable);
        List<ViewProductDto> viewProductDtoList=new ArrayList<>();
        products.forEach(product -> {
            viewProductDtoList.add(setViewProductDto(product));
        });
        return viewProductDtoList;

    }

    private ViewProductDto setViewProductDto(Product product){
        ViewProductDto viewProductDto=new ViewProductDto();
        viewProductDto.setId(product.getId());
        viewProductDto.setName(product.getName());
        viewProductDto.setBrand(product.getBrand());
        viewProductDto.setDescription(product.getDescription());
        viewProductDto.setCancellable(product.getCancellable());
        viewProductDto.setReturnable(product.getReturnable());
        viewProductDto.setActive(product.getActive());
        viewProductDto.setCategoryId(product.getCategory().getId());
        viewProductDto.setCategoryName(product.getCategory().getCategoryName());
        viewProductDto.setCategoryMetadataDtoList(getCategoryMetadata(product.getCategory().getId()));
        return viewProductDto;
    }
    public List<ViewAllProductVariationDto> getAllProductVariation(String size, String offset, String field, String order,Long productId,WebRequest webRequest){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable= PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);
        Optional<Product> productOptional=productRepository.findById(productId);
        if (!productOptional.isPresent()){
            String message="This product id is not valid";
            throw new CustomException(message);
        }
        Product product=productOptional.get();
        if (product.getDeleted()){
            String message="This product is deleted";
            throw new CustomException(message);
        }
        List<ProductVariation> productVariations=productVariationRepository.findByProductId(product.getId());
        System.out.println(productVariations.get(0).getId());
        List<ViewAllProductVariationDto> viewAllProductVariationDtos=new ArrayList<>();
        productVariations.forEach(productVariation -> {
            viewAllProductVariationDtos.add(setAllProductVariationDto(product,productVariation));
        });
        System.out.println(viewAllProductVariationDtos.get(1).getProductName());
        return viewAllProductVariationDtos;
    }
    private ViewAllProductVariationDto setAllProductVariationDto(Product product,ProductVariation productVariation){
        ViewAllProductVariationDto viewAllProductVariationDto = new ViewAllProductVariationDto();
        viewAllProductVariationDto.setProductVariationId(productVariation.getId());
        viewAllProductVariationDto.setProductName(product.getName());
        viewAllProductVariationDto.setProductBrand(product.getBrand());
        viewAllProductVariationDto.setProductDescription(product.getDescription());
        viewAllProductVariationDto.setVariationPrice(productVariation.getPrice());
        viewAllProductVariationDto.setVariationQuantityAvailable(productVariation.getQuantityAvailable());
        viewAllProductVariationDto.setVariationActive(productVariation.getActive());
        viewAllProductVariationDto.setPrimaryImageName(productVariation.getPrimaryImageName());
        if (productVariation.getSecondaryImageName()!=null)
            viewAllProductVariationDto.setSecondaryImageName(SetConverter.convertToSet(productVariation.getSecondaryImageName()));
        viewAllProductVariationDto.setMetadata(productVariation.getProductAttributes());
        viewAllProductVariationDto.setCancellable(product.getCancellable());
        viewAllProductVariationDto.setReturnable(product.getReturnable());
        viewAllProductVariationDto.setVariationActive(productVariation.getActive());
        return viewAllProductVariationDto;
    }
    public String deleteProduct(Long productId,HttpServletRequest httpServletRequest,WebRequest webRequest){
        String message;
        String sellerEmail=httpServletRequest.getUserPrincipal().getName();
        Optional<Product> productOptional=productRepository.findById(productId);
        if (!productOptional.isPresent()){
            message="Product Id is invalid!";
            throw new CustomException(message);
        }
        Product product= productOptional.get();
        if(!product.getSeller().getUser().getEmail().equals(sellerEmail)){
            message="This product deos not belongs to you";
            throw new CustomException(message);
        }
        productRepository.deleteById(productId);
        message="Product Deleted Successfully";
        return message;
    }

    public String updateProduct(UpdateProductDto updateProductDto,HttpServletRequest httpServletRequest,WebRequest webRequest){
        String message;
        String sellerEmail=httpServletRequest.getUserPrincipal().getName();
        Long productId=updateProductDto.getProductId();
        Optional<Product> productOptional=productRepository.findById(productId);
        if (!productOptional.isPresent()){
            message="Product id is Invalid";
            throw new CustomException(message);
        }
        Product product=productOptional.get();
        if (!product.getSeller().getUser().getEmail().equals(sellerEmail)){
            message="Product does not belongs to you!";
            throw new CustomException(message);
        }
        if (updateProductDto.getProductName()!=null){
            Boolean isUnique=isProductUnique(updateProductDto.getProductName(),product.getCategory().getId(),product.getSeller().getUser().getId(),product.getBrand());
            if (!isUnique){
                message="Product with this name already exist";
                throw new CustomException(message);
            }
        }
        setUpdateProductDto(product,updateProductDto);
        message="Product updated successfully";
        return message;
    }

    private void setUpdateProductDto(Product product, UpdateProductDto updateProductDto) {
        if (updateProductDto.getProductName()!=null)
            product.setName(updateProductDto.getProductName());
        if (updateProductDto.getDescription()!=null)
            product.setDescription(updateProductDto.getDescription());
        if (updateProductDto.getCancellable()!=null)
            product.setCancellable(updateProductDto.getCancellable());
        if (updateProductDto.getReturnable()!=null)
            product.setReturnable(updateProductDto.getReturnable());
        productRepository.save(product);
    }
    public String updateProductVariation(MultipartFile primaryImage, List<MultipartFile> secondaryImages, UpdateProductVariationDto updateProductVariationDto,HttpServletRequest httpServletRequest,WebRequest webRequest) throws IOException {
        String sellerEmail=httpServletRequest.getUserPrincipal().getName();
        String result=validateUpdateMetadata(updateProductVariationDto,sellerEmail);
        if (result.equalsIgnoreCase("Success")){
            ProductVariation productVariation=productVariationRepository.findById(updateProductVariationDto.getProductVariationId()).get();
            if (productVariation.getPrice()!=null)
                productVariation.setPrice(updateProductVariationDto.getPrice());
            if (productVariation.getQuantityAvailable()!=null)
                productVariation.setQuantityAvailable(updateProductVariationDto.getQuantityAvailable());
            if (primaryImage!=null)
            {
                String primaryImagePath=uploadImageDao.uploadProductVariationPrimaryImage(primaryImage,updateProductVariationDto.getProductVariationId());
                productVariation.setPrimaryImageName(primaryImagePath);
            }
            if (updateProductVariationDto.getMetadata()!=null)
                System.out.println("Maeatadata Updated");
                productVariation.setProductAttributes(updateProductVariationDto.getMetadata());
            if(!secondaryImages.isEmpty()){
                Set<String> secondaryImagesPath=uploadImageDao.uploadProductVariationSecondaryImages(secondaryImages,updateProductVariationDto.getProductVariationId());
                String imagesPath=SetConverter.convertToString(secondaryImagesPath);
                productVariation.setSecondaryImageName(imagesPath);
            }
            productVariationRepository.save(productVariation);
            return "Product Variation Updated Successfully!";
        }
        else {
            throw new CustomException(result);
        }

    }
    private String validateUpdateMetadata(UpdateProductVariationDto updateProductVariationDto,String sellerEmail){
        String message;
        Optional<ProductVariation> productVariationOptional=productVariationRepository.findById(updateProductVariationDto.getProductVariationId());
        if (!productVariationOptional.isPresent()){
            message="Product Variation Id is invalid";
            return message;
        }
        Product product=productVariationOptional.get().getProduct();

        Integer quantity=updateProductVariationDto.getQuantityAvailable();
        if (!product.getSeller().getUser().getEmail().equalsIgnoreCase(sellerEmail)){
            message="This is not your product";
            return message;
        }
        if (quantity!=null){
            if (quantity<0){
                message="Product quantity must not be negative";
                return message;
            }
        }
        if (updateProductVariationDto.getPrice()!=null){
            Double price=updateProductVariationDto.getPrice();
            if (price<0){
                message="Product price must not be negative";
                return message;
            }
        }

        if (product.getDeleted()){
            message="Product is deleted";
            return message;
        }
        if (!product.getActive()){
            message="Product is inactive";
            return message;
        }

        Category category=product.getCategory();
        if (updateProductVariationDto.getMetadata()!=null){
            Map<String,String> metadataMap=updateProductVariationDto.getMetadata();
            List<String> mapFields=new ArrayList<>(metadataMap.keySet());
            List<Object> metadataField=categoryRepository.getMetadataFieldsNameByCategoryId(category.getId());
            List<String> actualFields=new ArrayList<>();
            metadataField.forEach(field->{
                actualFields.add((String) field);
            });
            if(mapFields.size()<actualFields.size()){
                message="All category of metadata fields are not provided.";
                return message;
            }
            mapFields.removeAll(actualFields);
            if(!mapFields.isEmpty()){
                message="Fields are not associated with category";
                return message;
            }
            List<String> recievedField=new ArrayList<>(metadataMap.keySet());
            for (String field:recievedField) {
                CategoryMetadataField categoryMetadataField=categoryMetadataFieldRepository.findByName(field);
                List<Object>  objectList=categoryMetadataFieldValueRepository.getMetadataFieldValuesByCategoryAndFieldId(category.getId(),categoryMetadataField.getId());
                String values=objectList.get(0).toString();
                Set<String> actualValues= SetConverter.convertToSet(values);
                String recievedValue=metadataMap.get(field);
                if (recievedValue.isEmpty()){
                    System.out.println("Empty String");
                    message="Enter at least one value for field "+field;
                    return message;
                }
                Set<String> recievedValues=SetConverter.convertToSet(recievedValue);
                System.out.println(recievedValue+"-------"+field);
                Set<String> union=Sets.union(recievedValues,actualValues);
                if (union.size()>actualFields.size()){
                    message="Field value is Invalid for "+field;
                    return message;
                }
            }
        }
        return "Success";
    }
    public ProductDto getCustomerProduct(Long productId, WebRequest webRequest){
        String message;
        Optional<Product> productOptional=productRepository.findById(productId);
        if (!productOptional.isPresent()){
            message="Product id is invalid!";
            throw new CustomException(message);
        }
        Product product=productOptional.get();
        if (product.getDeleted()){
            message="Product is deleted!";
            throw new CustomException(message);
        }
        if (!product.getActive()){
            message="Product is inactive";
            throw new CustomException(message);
        }
        Category category=product.getCategory();
        List<ProductVariation> productVariations=productVariationRepository.findByProductId(productId);
        if (productVariations.isEmpty()){
            message="Product is not having any variation!";
            throw new CustomException(message);
        }
        ProductDto productDto =setViewCustomerProductDto(product,category,productVariations);
        return productDto;
    }

    private ProductDto setViewCustomerProductDto(Product product, Category category, List<ProductVariation> productVariations) {
        ProductDto productDto =new ProductDto();
        productDto.setName(product.getName());
        productDto.setBrand(product.getBrand());
        productDto.setDescription(product.getDescription());
        productDto.setCancellable(product.getCancellable());
        productDto.setReturnable(product.getReturnable());
        productDto.setCategoryId(category.getId());
        productDto.setCategoryName(category.getCategoryName());
        List<ViewCustomerProductVariationDto> variations=new ArrayList<>();
        productVariations.forEach(productVariation -> {
            ViewCustomerProductVariationDto variation=new ViewCustomerProductVariationDto();
            variation.setVariationId(productVariation.getId());
            variation.setVariationPrice(productVariation.getPrice());
            variation.setVariationQuantityAvailable(productVariation.getQuantityAvailable());
            variation.setVariationActive(productVariation.getActive());
            variation.setPrimaryImageName(productVariation.getPrimaryImageName());
            variation.setSecondaryImageName(SetConverter.convertToSet(productVariation.getSecondaryImageName()));
            variation.setMetadata(productVariation.getProductAttributes());
            variations.add(variation);
        });
        productDto.setProductVariationDtos(variations);
        return productDto;
    }
    public List<ViewAllProductDto> getAllProductCustomer(String size, String offset, String field, String order, Long categoryId, WebRequest webRequest){
        List<ViewAllProductDto> list=new ArrayList<>();
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable= PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);
        List<Category> leafCategory=categoryRepository.findByParentId(categoryId);
        if (leafCategory.isEmpty()){
            List<Product> products=productRepository.findByCategoryId(categoryId,pageable);
            products.forEach(product -> {
                if(!product.getDeleted() && product.getActive()){
                    ViewAllProductDto productCustomerDto=setProductList(product);
                    list.add(productCustomerDto);
                }
            });
            return list;
        }
        return null;
    }
    private ViewAllProductDto setProductList(Product product){
        ViewAllProductDto productCustomerDto=new ViewAllProductDto();
        productCustomerDto.setProductId(product.getId());
        productCustomerDto.setProductName(product.getName());
        productCustomerDto.setCategoryId(product.getCategory().getId());
        productCustomerDto.setCategoryName(product.getCategory().getCategoryName());
        List<CustomerVariationDto> variationDtos=new ArrayList<>();
        List<ProductVariation> variations=productVariationRepository.findByProductId(product.getId());
        variations.forEach(productVariation -> {
            CustomerVariationDto variationDto=new CustomerVariationDto();
            variationDto.setProductVariationId(productVariation.getId());
            variationDto.setPrimaryImage(productVariation.getPrimaryImageName());
            variationDto.setMetadata(productVariation.getProductAttributes());
            variationDtos.add(variationDto);
        });
        productCustomerDto.setVariations(variationDtos);
        return productCustomerDto;
    }
    public List<ViewAllProductDto> getSimilarProducts(String size, String offset, String field, String order, Long productId, WebRequest webRequest){
        Optional<Product> product=productRepository.findById(productId);
        if (!product.isPresent()){
            String message="Invalid Product Id !";
            throw new CustomException(message);
        }
        Long categoryId=product.get().getCategory().getId();
        return getAllProductCustomer(size,offset,field,order,categoryId,webRequest);
    }
    public ProductDto getAdminProduct(Long productId, WebRequest webRequest){
        String message;
        Optional<Product> productOptional=productRepository.findById(productId);
        if (!productOptional.isPresent()){
            message="Product id is invalid!";
            throw new CustomException(message);
        }
        Product product=productOptional.get();
        Category category=product.getCategory();
        List<ProductVariation> productVariations=productVariationRepository.findByProductId(productId);
        if (productVariations.isEmpty()){
            message="Product is not having any variation!";
            throw new CustomException(message);
        }
        ProductDto productDto =setViewCustomerProductDto(product,category,productVariations);
        return productDto;
    }
    public List<ViewAllProductDto> geAllProductAdmin(String size, String offset, String field, String order){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        Pageable pageable;
        if (order.equalsIgnoreCase("ASC"))
            pageable= PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        else
            pageable=PageRequest.of(pageOffset,pageSize, Sort.Direction.DESC,field);
        List<ViewAllProductDto> productDtoList=new ArrayList<>();
        List<Product> products= (List<Product>) productRepository.findAll();
        products.forEach(product -> {
            if (!product.getDeleted() && product.getActive()){
                productDtoList.add(setProductList(product));
            }
        });
        return productDtoList;
}

}
