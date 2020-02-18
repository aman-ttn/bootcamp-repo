//Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method called getDetails which provide there specific details like rateofinterest etc,print details of every banks
package program;

public class Question11 {
    public void getDetails(){
        System.out.println("Bankname:World bank");
        System.out.println("Address:Washington");
        System.out.println("Accout Type:Saving");
        System.out.println("Rate of interest:3");
    }
    class SBI{

        public void getDetails(){
            System.out.println("Bank name:SBI bank");
            System.out.println("Address:Delhi");
            System.out.println("Accout Type:Saving");
            System.out.println("Rate of interest:5");
        }
    }
    class BOI{

        public void getDetails(){
            System.out.println("Bankname:BOI bank");
            System.out.println("Address:Mumbai");
            System.out.println("Accout Type:Current");
            System.out.println("Rate of interest:4");
        }
    }
    class ICICI{

        public void getDetails(){
            System.out.println("Bankname:ICICI bank");
            System.out.println("Address:Noida");
            System.out.println("Accout Type:Saving");
            System.out.println("Rate of interest:5");
        }
    }

    public static void main(String[] args) {
        Question11 ob=new Question11();
        ob.getDetails();
        System.out.println();
        Question11.BOI b=ob.new BOI();
        Question11.SBI s=ob.new SBI();
        Question11.ICICI c=ob.new ICICI();
        b.getDetails();
        System.out.println();
        c.getDetails();
        System.out.println();
        s.getDetails();

    }
}
