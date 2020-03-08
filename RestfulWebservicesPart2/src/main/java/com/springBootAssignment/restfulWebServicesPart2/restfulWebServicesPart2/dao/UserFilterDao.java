package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.dao;

import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.UserFilter;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ApiModel(description = "This class is used in filters.It consist method of static and dynamic filtering.")
public class UserFilterDao {
    List<UserFilter> list=new ArrayList<>();
    public UserFilter getStaticUser(UserFilter userFilter){
        list.add(userFilter);
        UserFilter userFilter1=new UserFilter(userFilter.getId(),userFilter.getName(),userFilter.getPassword());
        return userFilter1;
    }

    public void getDynamicUser(UserFilter userFilter){
        list.add(userFilter);
    }

}
