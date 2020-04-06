package com.bootcamp.bootcampecomproject.entities;

import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.transaction.Transactional;
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
    private Boolean isDeleted;
    private Boolean isActive=false;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "UserRole",joinColumns = @JoinColumn(name="UserId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "RoleId",referencedColumnName = "id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Seller seller;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Admin admin;

    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name=" + name +
                ", password='" + password + '\'' +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", roles=" + roles +
                ", customer=" + customer +
                ", seller=" + seller +
                '}';
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
