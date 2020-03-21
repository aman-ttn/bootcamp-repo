package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Seller {



    @Id
    private String gst;
    private String companyContact;
    private String companyName;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public String getGst() {
        return gst;
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

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }
}
