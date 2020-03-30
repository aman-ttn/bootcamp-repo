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
}
