package com.bootcamp.bootcampecomproject.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders;


    private String contactNumber;


    @MapsId
    @OneToOne
    @JoinColumn(name="UserId")
    private User user;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addresses;

    public List<Orders> getOrders() { return orders; }

    public void setOrders(List<Orders> orders) { this.orders = orders; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getContactNumber() { return contactNumber; }

    public void setContactNumber(String number) { this.contactNumber = number; }

    public User getUser() {
        return user; }

    public void setUser(User user) { this.user = user; }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
