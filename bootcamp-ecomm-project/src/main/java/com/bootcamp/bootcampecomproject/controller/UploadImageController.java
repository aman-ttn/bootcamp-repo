package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.UploadImageDao;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class UploadImageController {
    @Autowired
    private UploadImageDao uploadImageDao;
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestBody MultipartFile image, HttpServletRequest httpServletRequest) throws IOException {
        return uploadImageDao.uploadImage(image,httpServletRequest);
    }
}
