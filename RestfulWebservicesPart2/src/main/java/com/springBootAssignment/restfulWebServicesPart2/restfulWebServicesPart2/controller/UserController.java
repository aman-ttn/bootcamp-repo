package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.controller;

import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.dao.UserDao;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.Users;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ApiModel(description = "This is Controller to add,delete and get the user")
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;


//    Commenting the api of question 11 because HATEOAS is not working with swagger
//    @ApiModelProperty(notes = "This is used to add the User")
//    @PostMapping(path="/users")
//    public EntityModel addUser(@RequestBody Users user){
//
//
//            userDao.addUsers(user);
//            EntityModel<Users> model=new EntityModel<>(user);
//            WebMvcLinkBuilder linkTo=linkTo(methodOn(this.getClass()).addUser(user));
//            model.add(linkTo.withRel("All-User"));
//            return model;
//
//        }


    @ApiModelProperty(notes = "This is used to get the User")
    @GetMapping(path="/users")
    public List<Users> getUser(){
        return userDao.getUsers();
    }

    @ApiModelProperty(notes = "This is used to delete the User")
    @DeleteMapping(path="/users/{id}")
    public List<Users> deleteUser(@PathVariable Integer id){
        return userDao.deleteUser(id);
    }

}
