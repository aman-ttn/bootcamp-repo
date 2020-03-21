package com.bootcamp.bootcampecomproject.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer  implements Serializable{


    @Id
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private String number;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
