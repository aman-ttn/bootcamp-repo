package com.bootcamp.assignmentjpa2.entities.singleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fp")
public class Footballplayersingle extends Playersingle {
    private Integer goal;

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }
}
