package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;

@Entity
public class OrderStatus {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "orderProductId")
    private OrderProduct orderProduct;
    @Enumerated(EnumType.STRING)
    private FromStatus fromStatus;
    @Enumerated(EnumType.STRING)
    private ToStatus toStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public FromStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(FromStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public ToStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(ToStatus toStatus) {
        this.toStatus = toStatus;
    }
}
