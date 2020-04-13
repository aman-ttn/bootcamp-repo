package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.User;
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

@Component
public class UploadImageDao {
    @Autowired
    UserRepository userRepository;
    public String uploadImage(MultipartFile image, HttpServletRequest httpServletRequest) throws IOException {


        if (image.isEmpty()) {
            return "Please select a file to upload";
        }
        try {
            String folder = "/home/aman/image/";
            byte[] bytes = image.getBytes();
            User user=getUser(httpServletRequest);
            String fileName=getFileName(image.getOriginalFilename(),user);
            Path path = Paths.get(folder + fileName);
//            String mimetype= new MimetypesFileTypeMap().getContentType((File) image);
//            String type = mimetype.split("/")[0];
//            type.equals("image");
            Files.write(path, bytes);
            saveFilePath(fileName,user);
            return "You successfully uploaded ";

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    private String getFileName(String fileName,User user){
        Integer l=fileName.lastIndexOf(".");
        fileName=fileName.substring(l);
        Long id=user.getId();
        return  (id+fileName);
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
}
