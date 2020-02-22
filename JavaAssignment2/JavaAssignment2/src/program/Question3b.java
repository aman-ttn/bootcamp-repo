package program;

public class Question3b {
    public static void main(String[] args) {
        try{
            Class.forName("java.util");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
