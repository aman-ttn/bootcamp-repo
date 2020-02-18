//Write a program to display values of enums using a constructor & getPrice() method (Example display house & their prices)
package program;



    public class Question9 {
        enum house {
            A(90000),
            B(80000),
            C(76000);
            public int price;
             house(int price){
                this.price=price;
            }
            public static void getPrice() {
                for(house h:house.values())
                    System.out.println(h.price);
            }

            }

        public static void main(String[] args) {
            System.out.println("House and their prices");
            house.getPrice();
        }
        }


