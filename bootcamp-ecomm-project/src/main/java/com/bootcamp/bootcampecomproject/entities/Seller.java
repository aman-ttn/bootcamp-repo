package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String gst;

    private String companyContact;
    private String companyName;

    @MapsId
    @OneToOne
    @JoinColumn(name="UserId")
    private User user;

    @OneToOne(mappedBy = "seller",cascade = CascadeType.ALL)
    private Address address;


    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private List<Product> product;

    public String getGst() {
        return gst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
