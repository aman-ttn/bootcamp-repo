package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDao {

    @Autowired
    OrderRepository orderRepository;
    public void saveOrder(Long id){

    }
}
