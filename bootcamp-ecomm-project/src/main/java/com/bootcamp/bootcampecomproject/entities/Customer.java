package com.bootcamp.bootcampecomproject.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private List<Order> orders;





    private String number;

    @MapsId
    @OneToOne
    @JoinColumn(name="UserId")
    private User user;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addresses;

//    public List<Order> getOrders() { return orders; }
//
//    public void setOrders(List<Order> orders) { this.orders = orders; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<Address> getAddresses() { return addresses; }

    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public User getUser() {
        return user; }

    public void setUser(User user) { this.user = user; }


}
