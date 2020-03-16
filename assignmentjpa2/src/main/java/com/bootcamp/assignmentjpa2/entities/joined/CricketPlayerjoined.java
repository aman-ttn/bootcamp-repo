package com.bootcamp.assignmentjpa2.entities.joined;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="cricket")
@PrimaryKeyJoinColumn(name="id")
public class CricketPlayerjoined extends Playerjoined {
    private Integer run;
    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

}
