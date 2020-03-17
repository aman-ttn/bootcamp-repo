package com.assignmentjpa3.assignmentjpa3.dao;

import com.assignmentjpa3.assignmentjpa3.entities.Address;
import com.assignmentjpa3.assignmentjpa3.entities.Author;
import com.assignmentjpa3.assignmentjpa3.entities.manytomany.Authormanytomany;
import com.assignmentjpa3.assignmentjpa3.entities.manytomany.Bookmanytomany;
import com.assignmentjpa3.assignmentjpa3.entities.onetomany.Authoronetomany;
import com.assignmentjpa3.assignmentjpa3.entities.onetomany.Bookonetomany;
import com.assignmentjpa3.assignmentjpa3.entities.onetoone.Authorone;
import com.assignmentjpa3.assignmentjpa3.entities.Subject;
import com.assignmentjpa3.assignmentjpa3.entities.onetoone.Bookone;
import com.assignmentjpa3.assignmentjpa3.repositories.AuthorManytoManyRepository;
import com.assignmentjpa3.assignmentjpa3.repositories.AuthorOnetoManyRepository;
import com.assignmentjpa3.assignmentjpa3.repositories.AuthorOnetoOneRepository;
import com.assignmentjpa3.assignmentjpa3.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDao {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorOnetoOneRepository authorOnetoOneRepository;

    @Autowired
    AuthorOnetoManyRepository authorOnetoManyRepository;

    @Autowired
    AuthorManytoManyRepository authorManytoManyRepository;

    public void doInsert(){

        Author author=new Author();
        author.setId(1);
        author.setName("Aman");

        Address address=new Address();
        address.setLocation("Noida");
        address.setState("U.P");
        address.setStreetnumber(11);
        author.setAddress(address);

        List<Subject> subjectList=new ArrayList<Subject>();
        Subject subject=new Subject();
        subject.setSubid(1);
        subject.setSubject1("Math");
        subject.setSubject2("Phy");
        subject.setSubject3("Bio");
        subjectList.add(subject);
        subject.setAuthor(author);
        author.setSubjectList(subjectList);

        authorRepository.save(author);

    }

    public void doInsertBookOnetoOne(){

//      One author is having the the one book.

        Authorone authorone=new Authorone();
        authorone.setId(2);
        authorone.setName("Auro");

        Address address=new Address();
        address.setLocation("Delhi");
        address.setState("Delhi");
        address.setStreetnumber(141);
        authorone.setAddress(address);

        Bookone bookone=new Bookone();
        bookone.setAuthorone(authorone);
        bookone.setBookid(1);
        bookone.setBookname("Alchemist");
        authorone.setBookone(bookone);

        authorOnetoOneRepository.save(authorone);
    }

    public void doInsertBookOneToMany(){

//        One author is having the the list of three books.

        Authoronetomany authoronetomany=new Authoronetomany();
        authoronetomany.setId(1);
        authoronetomany.setName("Anuj");

        Address address=new Address();
        address.setLocation("Kanpur");
        address.setState("UP");
        address.setStreetnumber(14);
        authoronetomany.setAddress(address);

        List<Bookonetomany> bookonetomanyList=new ArrayList<>();

        Bookonetomany bookonetomany1=new Bookonetomany();
        bookonetomany1.setBookid(1);
        bookonetomany1.setBookname("As you like it.");
        bookonetomany1.setAuthoronetomany(authoronetomany);
        bookonetomanyList.add(bookonetomany1);

        Bookonetomany bookonetomany2=new Bookonetomany();
        bookonetomany2.setBookid(2);
        bookonetomany2.setBookname("Much Ado About Nothing");
        bookonetomany2.setAuthoronetomany(authoronetomany);
        bookonetomanyList.add(bookonetomany2);

        Bookonetomany bookonetomany3=new Bookonetomany();
        bookonetomany3.setBookid(3);
        bookonetomany3.setBookname("The Invisible man.");
        bookonetomany3.setAuthoronetomany(authoronetomany);
        bookonetomanyList.add(bookonetomany3);

        authoronetomany.setBookonetomany(bookonetomanyList);

        authorOnetoManyRepository.save(authoronetomany);
    }

    public void doInsertBookManytoMany(){

        Address address=new Address();
        address.setLocation("Kanpur");
        address.setState("UP");
        address.setStreetnumber(14);

//        Two author is having the the list of two books.

        Authormanytomany authormanytomany=new Authormanytomany();
        authormanytomany.setId(1);
        authormanytomany.setName("Aman");
        authormanytomany.setAddress(address);

        Authormanytomany authormanytomany2=new Authormanytomany();
        authormanytomany2.setId(2);
        authormanytomany2.setName("Ajay");
        authormanytomany2.setAddress(address);

        List<Bookmanytomany> bookmanytomanyList=new ArrayList<>();

        Bookmanytomany bookmanytomany=new Bookmanytomany();
        bookmanytomany.setBookid(1);
        bookmanytomany.setBookname("As you like it");
        bookmanytomanyList.add(bookmanytomany);

        Bookmanytomany bookmanytomany2=new Bookmanytomany();
        bookmanytomany2.setBookid(2);
        bookmanytomany2.setBookname("Short stories");
        bookmanytomanyList.add(bookmanytomany2);

        authormanytomany.setBookmanytomanyList(bookmanytomanyList);
        authormanytomany2.setBookmanytomanyList(bookmanytomanyList);
        authorManytoManyRepository.save(authormanytomany);
        authorManytoManyRepository.save(authormanytomany2);


    }

}
