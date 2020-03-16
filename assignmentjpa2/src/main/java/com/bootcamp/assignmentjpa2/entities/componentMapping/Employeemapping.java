package com.bootcamp.assignmentjpa2.entities.componentMapping;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employeemapping")
public class Employeemapping {
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
    @Embedded
    private Salarymapping salaryMapping;

    public Salarymapping getSalaryMapping() {
        return salaryMapping;
    }

    public void setSalaryMapping(Salarymapping salaryMapping) {
        this.salaryMapping = salaryMapping;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
