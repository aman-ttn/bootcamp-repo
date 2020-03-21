package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Embedded
    private Name name;
    private String password;
    private boolean isDeleted;
    private boolean isActive;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole",joinColumns = @JoinColumn(name="UserId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "RoleId",referencedColumnName = "id"))
    private List<Role> roles;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Seller seller;



    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
