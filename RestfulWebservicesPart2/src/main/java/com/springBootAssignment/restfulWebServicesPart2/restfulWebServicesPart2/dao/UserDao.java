package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.dao;

import com.google.common.net.MediaType;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.Users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@ApiModel(description = "This consist of main logic of adding adding,getting and deleting the user")
@Component
public class UserDao {
    int countUser;
    @ApiModelProperty(notes = "Adding a user")
    List<Users> list=new ArrayList<Users>();
    public void addUsers(Users user){
        countUser++;
        if(user.getId()==null){
            user.setId(countUser);
        }
        list.add(user);
    }

    @ApiModelProperty(notes = "Getting a users")
    public List<Users> getUsers(){
        return list;
    }

//    @ApiModelProperty(notes = "Deleting a user")
    @ApiOperation("Deleting -------")
    public List<Users> deleteUser(Integer id){
        Iterator<Users> iterator=list.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId()==id){
                iterator.remove();
            }
        }
        return list;
    }
}
