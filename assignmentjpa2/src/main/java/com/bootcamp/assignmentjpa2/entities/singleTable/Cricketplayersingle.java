package com.bootcamp.assignmentjpa2.entities.singleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cp")
public class Cricketplayersingle extends Playersingle {
    private Integer run;
    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

}
