package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.exception.CustomException;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UploadImageDao {
    @Autowired
    UserRepository userRepository;
    public String uploadImage(MultipartFile image, HttpServletRequest httpServletRequest) throws IOException {


        if (image.isEmpty()) {
            return "Please select a file to upload";
        }
        try {
            String folder = "/home/aman/EcommProject-Images/users/";
            byte[] bytes = image.getBytes();
            User user=getUser(httpServletRequest);
            String fileName=getFileName(image.getOriginalFilename(),user.getId());
            Path path = Paths.get(folder + fileName);
            Files.write(path, bytes);
            saveFilePath(folder+fileName,user);
            return "You successfully uploaded ";

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    private String getFileName(String fileName,Long id){
        Integer l=fileName.lastIndexOf(".");
        fileName=fileName.substring(l);
        if(fileName.equalsIgnoreCase(".jpg") || fileName.equalsIgnoreCase(".jpeg") ||
                fileName.equalsIgnoreCase(".png") || fileName.equalsIgnoreCase(".bmp"))
            return  (id+fileName);
        else
            throw new CustomException("Image format is not valid!");
    }
    private void saveFilePath(String filePath,User user){
        user.setImagePath(filePath);
        userRepository.save(user);
    }
    private User getUser(HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String email=principal.getName();
        User user=userRepository.findByEmail(email);
        return user;
    }
    public String uploadProductImage(MultipartFile image,Long productId) throws IOException {
        if (image.isEmpty()) {
            return "Please select a file to upload";
        }
        String folder = "/home/aman/EcommProject-Images/products/product_id/";
        byte[] bytes = image.getBytes();
        String fileName=getFileName(image.getOriginalFilename(),productId);
        Path path = Paths.get(folder + fileName);
        Files.write(path, bytes);
        return folder+fileName;
    }
    public String uploadProductVariationPrimaryImage(MultipartFile image,Long productId) throws IOException {
        if (image.isEmpty()) {
            return "Please select a file to upload";
        }
        String folder = "/home/aman/EcommProject-Images/products/product_id/variations/";
        byte[] bytes = image.getBytes();
        String fileName=getFileName(image.getOriginalFilename(),productId);
        Path path = Paths.get(folder + fileName);
        Files.write(path, bytes);
        return (folder+fileName);
    }
    public Set<String> uploadProductVariationSecondaryImages(List<MultipartFile> images,Long productId) throws IOException {
        Integer count=0;
        Set<String> pathNames=new HashSet<>();
        for (MultipartFile image:images) {
            count++;
            String folder = "/home/aman/EcommProject-Images/products/product_id/variations/";
            byte[] bytes = image.getBytes();
            String fileName=getSecondaryFileName(image.getOriginalFilename(),productId,count);
            Path path = Paths.get(folder + fileName);
            Files.write(path, bytes);
            pathNames.add(folder+fileName);
        }
        return pathNames;
    }
    private String getSecondaryFileName(String fileName,Long id,Integer count){
        Integer l=fileName.lastIndexOf(".");
        fileName=fileName.substring(l);
        if(fileName.equalsIgnoreCase(".jpg") || fileName.equalsIgnoreCase(".jpeg") ||
                fileName.equalsIgnoreCase(".png") || fileName.equalsIgnoreCase(".bmp"))
            return  (id+"."+count+fileName);
        else
            throw new CustomException("Image format is not valid!");
    }

}
