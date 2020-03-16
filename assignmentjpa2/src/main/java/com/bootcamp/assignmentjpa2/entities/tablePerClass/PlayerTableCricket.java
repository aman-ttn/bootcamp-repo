package com.bootcamp.assignmentjpa2.entities.tablePerClass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cricketplayer")
public class PlayerTableCricket extends PlayerTable {
    private Integer run;
    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

}
