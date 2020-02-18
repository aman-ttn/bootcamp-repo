//Write a single program for following operation using overloading
//        A) Adding 2 integer number
//        B) Adding 2 double
//        C) multiplying 2 float
//        D) multiplying 2 int
//        E) concate 2 string
//        F) Concate 3 String
package program;

public class Question10 {
    public static void main(String[] args) {
    Question10 ob=new Question10();
        System.out.println(ob.add(5,7));
        System.out.println(ob.add(5.0f,9.3f));
        System.out.println(ob.add(5.7,6.8));
        System.out.println(ob.add(7,11));
        System.out.println(ob.mul(9,4));
        System.out.println(ob.mul(5.0f,9.8f));
        System.out.println(ob.con("Hello","Aman"));
        System.out.println(ob.con("Hello","Aman","Saini"));

    }
    public Integer add(Integer a,Integer b){
        return(a+b);
    }
    public double add(double a,double b){
        return(a+b);
    }
    public float add(float a,float b){
        return(a+b);
    }
    public int add(int a,int b){
        return(a+b);
    }
    public float mul(float a,float b){
        return(a*b);
    }
    public int mul(int a,int b){
        return(a*b);
    }
    public String con(String a,String b){
        String str=a.concat(b);
        return str;
    }
    public String con(String a,String b,String c){
        String str=a.concat(b).concat(c);
        return str;
    }
}
