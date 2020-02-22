//Create Java classes having suitable attributes for Library management system.
// Use OOPs concepts in your design.Also try to use interfaces and abstract classes.
package program;
import java.util.Scanner;
interface bookInter{
    public void bookName(String name);
    public void bookPrice(int pri);
    public void bookPublisher(String pub);
}
interface libraryInter {
    public void bookCount(int bcount);

    public void memberCount(int mcount);

    public void libraryName(String name);
}
interface memberInter {
    public void studentsCount(int scount);

    public void teacherCount(int mcount);

}
class Book implements bookInter {
    public void bookName(String name) {
        System.out.println("Name of book:"+name);
    }
    public void bookPrice(int pri) {
        System.out.println("Price of book:"+pri);

    }
    public void bookPublisher(String pub) {
        System.out.println("Name of publication:"+pub);
    }
    public void bookType(String booktype){
        System.out.println("Type of book:"+booktype);
    }
}
class Library implements libraryInter{

    public void bookCount(int bcount) {
        System.out.println("Total no. of books:"+bcount);
    }

    public void memberCount(int mcount) {
        System.out.println("Total no. of members:"+mcount);

    }

    public void libraryName(String name) {
        System.out.println("Name of books:"+name);

    }
}
class Members implements memberInter{

    public void studentsCount(int scount) {
        System.out.println("Total no. of students:"+scount);
    }



    public void teacherCount(int tcount) {
        System.out.println("Total no. of teachers:"+tcount);

    }


}
class Question1 {
    public static void main(String[] args) {
        System.out.println("-------Record of book-------");
        Book book=new Book();
        book.bookName("Alchemist");
        book.bookPrice(999);
        book.bookPublisher("Pablo");
        book.bookType("Novel");
        System.out.println("--------Record of Library---------");
        Library library=new Library();
        library.libraryName("Wilson");
        library.bookCount(978);
        library.memberCount(500);
        System.out.println("--------Record of Members---------");
        Members member=new Members();
        member.studentsCount(500);
        member.teacherCount(50);
 }
}
