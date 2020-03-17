package com.assignmentjpa3.assignmentjpa3.entities.onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Bookone {
    @Id
    private Integer bookid;
    private String bookname;

    public Authorone getAuthorone() {
        return authorone;
    }

    public void setAuthorone(Authorone authorone) {
        this.authorone = authorone;
    }

    @OneToOne
    @JoinColumn(name="author_id")
    private Authorone authorone;



    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }


    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
