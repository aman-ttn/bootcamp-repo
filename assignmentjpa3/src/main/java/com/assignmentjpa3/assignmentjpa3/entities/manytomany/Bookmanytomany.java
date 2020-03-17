package com.assignmentjpa3.assignmentjpa3.entities.manytomany;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

//5)-Create an Entity book with an instance variable bookName.

@Entity
public class Bookmanytomany {
    @Id
    private Integer bookid;
    private String bookname;
    @ManyToMany(mappedBy = "bookmanytomanyList")
    private List<Authormanytomany> authormanytomanyList;

    public List<Authormanytomany> getAuthormanytomanyList() {
        return authormanytomanyList;
    }

    public void setAuthormanytomanyList(List<Authormanytomany> authormanytomanyList) {
        this.authormanytomanyList = authormanytomanyList;
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
