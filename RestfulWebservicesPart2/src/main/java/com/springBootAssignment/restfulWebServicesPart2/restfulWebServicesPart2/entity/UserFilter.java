package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

@JsonFilter("UserFilter")
@ApiModel(description = "This class is used in filters.It take id,name and age in its constructor.")
public class UserFilter {

    Integer id;
    String name;
//    @JsonIgnore
    String password;
    public UserFilter(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
