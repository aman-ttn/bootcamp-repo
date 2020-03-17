package com.assignmentjpa3.assignmentjpa3.entities.manytomany;

import com.assignmentjpa3.assignmentjpa3.entities.Address;

import javax.persistence.*;
import java.util.List;
// 8)-Implement Many to Many Mapping between Author and Book.
@Entity
public class Authormanytomany {
    @Id
    private Integer id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "authorsandbooks",joinColumns = @JoinColumn(name="author_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id",referencedColumnName = "bookid"))
    private List<Bookmanytomany> bookmanytomanyList;
    @Embedded
    private Address address;



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

    public List<Bookmanytomany> getBookmanytomanyList() {
        return bookmanytomanyList;
    }

    public void setBookmanytomanyList(List<Bookmanytomany> bookmanytomanyList) {
        this.bookmanytomanyList = bookmanytomanyList;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
