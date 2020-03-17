package com.assignmentjpa3.assignmentjpa3.entities.onetoone;

import com.assignmentjpa3.assignmentjpa3.entities.Address;
import com.assignmentjpa3.assignmentjpa3.entities.Subject;

import javax.persistence.*;
import java.util.List;
//6)-Implement One to One mapping between Author and Book.
@Entity
public class Authorone {
    @Id
    private Integer id;

    private String name;

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
//    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
//    private List<Subject> subjectList;



//    @OneToMany(mappedBy = "Author",cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "authorone",cascade = CascadeType.ALL)
    private Bookone bookone;

    public Bookone getBookone() {
        return bookone;
    }

    public void setBookone(Bookone bookone) {
        this.bookone = bookone;
    }


    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
