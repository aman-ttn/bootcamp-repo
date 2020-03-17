package com.assignmentjpa3.assignmentjpa3.entities.onetomany;

import com.assignmentjpa3.assignmentjpa3.entities.Address;
import com.assignmentjpa3.assignmentjpa3.entities.onetoone.Bookone;

import javax.persistence.*;
import java.util.List;

//Implement One to Many Mapping between Author and Book
// (Unidirectional, BiDirectional and without additional table )
//        and implement cascade save.
@Entity
public class Authoronetomany {
    @Id
    private Integer id;
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "authoronetomany",cascade = CascadeType.ALL)
    private List<Bookonetomany> bookonetomany;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Bookonetomany> getBookonetomany() {
        return bookonetomany;
    }

    public void setBookonetomany(List<Bookonetomany> bookonetomany) {
        this.bookonetomany = bookonetomany;
    }







    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
