package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Category {
    @Id
    private Long id;
    private String name;
    private Long parentId;

}
