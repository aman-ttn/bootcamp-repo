package com.assignmentjpa3.assignmentjpa3.entities.onetomany;

import com.assignmentjpa3.assignmentjpa3.entities.onetoone.Authorone;

import javax.persistence.*;

//5)-Create an Entity book with an instance variable bookName.
@Entity
public class Bookonetomany {
    @Id
    private Integer bookid;
    private String bookname;


//comment ManyToOne annotation in case of unidirectional
    @ManyToOne
    @JoinColumn(name="author_id")
    private Authoronetomany authoronetomany;

    public Authoronetomany getAuthoronetomany() {
        return authoronetomany;
    }

    public void setAuthoronetomany(Authoronetomany authoronetomany) {
        this.authoronetomany = authoronetomany;
    }




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
