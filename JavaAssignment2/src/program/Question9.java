//Design classes having attributes for furniture where there are wooden chairs and tables, metal chairs and tables.
// There are stress and fire tests for each products.
package program;
interface test{
    public void stressStatus(boolean press);
    public void fireStatus(boolean tempe);
}
public class Question9 extends Furniture{
    public static void main(String[] args) {
        Furniture f=new Furniture();

        Wooden w=f.new Wooden();
        Metal m=f.new Metal();

        Wooden.Chair wc=w.new Chair();
        Wooden.Table wt=w.new Table();

        Metal.Chair mc=m.new Chair();
        Metal.Table mt=m.new Table();

        System.out.println("Data for wooden chair----------");
        wc.fireStatus(true);
        wc.stressStatus(false);
        wt.fireStatus(false);
        wt.stressStatus(true);

        System.out.println("Data for metal chair----------");
        mc.fireStatus(true);
        mc.stressStatus(false);
        mt.fireStatus(false);
        mt.stressStatus(true);
    }
}
class Furniture{
    class Wooden {
        class Chair implements test {

            public void stressStatus(boolean press) {
                System.out.println("Stress test of Wooden chair:"+press);
            }

            public void fireStatus(boolean temp) {
                System.out.println("Fire test of Wooden chair:"+temp);

            }
        }
        class Table implements test {

            public void stressStatus(boolean press) {
                System.out.println("Stress test of Wooden chair:"+press);

            }

            public void fireStatus(boolean temp) {
                System.out.println("Stress test of Wooden chair:"+temp);

            }
        }
    }
        class Metal {
            class Chair implements test {

                public void stressStatus(boolean press) {
                    System.out.println("Stress test of Metal chair:"+press);
                }

                public void fireStatus(boolean temp) {
                    System.out.println("Fire test of Metal chair:"+temp);

                }
            }
            class Table implements test {

                public void stressStatus(boolean press) {
                    System.out.println("Stress test of Metal chair:"+press);

                }

                public void fireStatus(boolean temp) {
                    System.out.println("Stress test of Metal chair:"+temp);

                }
            }
        }
    }


