package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.AddProductDto;
import com.bootcamp.bootcampecomproject.dtos.AddProductVariationDto;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.CategoryException;
import com.bootcamp.bootcampecomproject.exception.CustomException;
import com.bootcamp.bootcampecomproject.exception.ProductException;
import com.bootcamp.bootcampecomproject.repositories.*;
import com.bootcamp.bootcampecomproject.utils.SetConverter;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
        Locale locale=webRequest.getLocale();
        Product product=productRepository.findById(productId).get();
        String sellerEmail=product.getSeller().getUser().getEmail();
        product.setActive(true);
        productRepository.save(product);
        String message=messageSource.getMessage("productActivation.successful",null,locale);
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
        System.out.println("Before product-------");
        Product product=productRepository.findById(addProductVariationDto.getProductId()).get();
        System.out.println("After product-------");
        if (product==null){
            message="Product Id is invalid";
            return message;
        }

        Integer quantity=addProductVariationDto.getQuantityAvailable();
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
        System.out.println("Hello-------");
        List<Object> metadataField=categoryRepository.getMetadataFieldsNameByCategoryId(category.getId());
        System.out.println("Bye+-----------");
        List<String> actualFields=new ArrayList<>();
        metadataField.forEach(field->{
            actualFields.add((String) field);
        });
        if(mapFields.size()<actualFields.size()){
            message="All category of metadata fields are not provided.";
        }
        mapFields.removeAll(actualFields);
        if(!mapFields.isEmpty()){
            message="Fields are not associated with category";
            return message;
        }
        List<String> recievedField=new ArrayList<>();
        for (String field:recievedField) {
            CategoryMetadataField categoryMetadataField=categoryMetadataFieldRepository.findByName(field);
            List<Object>  objectList=categoryMetadataFieldValueRepository.getMetadataFieldValuesByCategoryAndFieldId(category.getId(),categoryMetadataField.getId());
            String values=objectList.get(0).toString();
            Set<String> valueSet= SetConverter.convertToSet(values);

            String recievedValues=metadataMap.get(field);
            Set<String> recievedValueSet=SetConverter.convertToSet(recievedValues);

            if (!Sets.difference(valueSet,recievedValueSet).isEmpty()){
                message="Field value is Invalid for "+field;
                return message;
            }
        }
        return "Success";
    }
}
