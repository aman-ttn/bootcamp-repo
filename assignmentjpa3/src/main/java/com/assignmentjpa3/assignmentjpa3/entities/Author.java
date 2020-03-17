package com.assignmentjpa3.assignmentjpa3.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Author {
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

//    3)-Introduce a List of subjects for author.

    public void setId(Integer id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Subject> subjectList;





    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

//   2)- Create instance variable of Address class inside Author class and save it as embedded object.

    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
