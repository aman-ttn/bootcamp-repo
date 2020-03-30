package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "orders",cascade = CascadeType.ALL)
    private OrderProduct orderProduct;

    @OneToOne(mappedBy = "orders",cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "customerUserId")
    private Customer customer;

    private Double amountPaid;
    private Date dateCreated;
    private String paymentMethod;
    private String customerAddressCity;
    private String customerAddressState;
    private String customerAddressStreetLine;
    private Integer customerAddressZipCode;
    private String customerAddressLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerAddressCity() {
        return customerAddressCity;
    }

    public void setCustomerAddressCity(String customerAddressCity) {
        this.customerAddressCity = customerAddressCity;
    }

    public String getCustomerAddressState() {
        return customerAddressState;
    }

    public void setCustomerAddressState(String customerAddressState) {
        this.customerAddressState = customerAddressState;
    }

    public String getCustomerAddressStreetLine() {
        return customerAddressStreetLine;
    }

    public void setCustomerAddressStreetLine(String customerAddressStreetLine) {
        this.customerAddressStreetLine = customerAddressStreetLine;
    }

    public Integer getCustomerAddressZipCode() {
        return customerAddressZipCode;
    }

    public void setCustomerAddressZipCode(Integer customerAddressZipCode) {
        this.customerAddressZipCode = customerAddressZipCode;
    }

    public String getCustomerAddressLabel() {
        return customerAddressLabel;
    }

    public void setCustomerAddressLabel(String customerAddressLabel) {
        this.customerAddressLabel = customerAddressLabel;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customer=" + customer +
                ", amountPaid=" + amountPaid +
                ", dateCreated=" + dateCreated +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", customerAddressCity='" + customerAddressCity + '\'' +
                ", customerAddressState='" + customerAddressState + '\'' +
                ", customerAddressStreetLine='" + customerAddressStreetLine + '\'' +
                ", customerAddressZipCode=" + customerAddressZipCode +
                ", customerAddressLabel='" + customerAddressLabel + '\'' +
                '}';
    }
}
