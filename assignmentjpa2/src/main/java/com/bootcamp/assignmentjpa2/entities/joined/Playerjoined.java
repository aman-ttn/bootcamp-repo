package com.bootcamp.assignmentjpa2.entities.joined;


import javax.persistence.*;

@Entity
@Table(name="player")
@Inheritance(strategy = InheritanceType.JOINED)
public class Playerjoined {
    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
