package com.bootcamp.assignmentjpa2.entities.tablePerClass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="footballplayer")
public class PlayerTableFootball extends PlayerTable {
    private Integer goal;

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }
}
