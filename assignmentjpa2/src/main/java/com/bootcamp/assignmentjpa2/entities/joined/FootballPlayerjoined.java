package com.bootcamp.assignmentjpa2.entities.joined;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="football")
@PrimaryKeyJoinColumn(name="id")
public class FootballPlayerjoined extends Playerjoined {
    private Integer goal;

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }
}
