package com.assignmentjpa3.assignmentjpa3.controller;

import com.assignmentjpa3.assignmentjpa3.dao.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    AuthorDao authorDao;

    @GetMapping(path="/authors")
    public String insertAuthor(){
        authorDao.doInsert();
        return "Insertion Succesfull";
    }
//    Implement One to One mapping between Author and Book.
    @GetMapping(path="/authors/books/onetoone")
    public void insertBookOnetoOne(){
        authorDao.doInsertBookOnetoOne();
    }

//    Implement One to Many Mapping between Author and Book(Unidirectional, BiDirectional and without additional table ) and implement cascade save.
     @GetMapping(path="/authors/books/onetomany")
    public void insertBookOnetoMany(){
        authorDao.doInsertBookOneToMany();
    }

//    Implement Many to Many Mapping between Author and Book.
    @GetMapping(path="/authors/books/manytomany")
    public void insertBookManytoMany(){
        authorDao.doInsertBookManytoMany();
    }

//   9)- Which method on the session object can be used to remove an object from the cache?
//   Answer-) evict() method is used to remove a particular object from cache associated with session.

//    10)-What does @transactional annotation do?
//    Answer-)The transactional annotation itself defines the scope of a single database transaction.
//    The database transaction happens inside the scope of apersistence context.
}
