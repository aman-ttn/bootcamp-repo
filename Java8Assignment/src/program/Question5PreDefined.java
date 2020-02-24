//Implement following functional interfaces from java.util.function using lambdas:
//
//(1) Consumer
//
//(2) Supplier
//
//(3) Predicate
//
//(4) Function
package program;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Question5PreDefined {
    public static void main(String[] args) {
        Consumer consumer=(e)->{
            System.out.println("(Consumer)->>The square is:"+((int)e*(int)e));
        };
        consumer.accept(4);
        Supplier supplier=()->{
          return "(Supplier)->>This is a supplier";
        };
        System.out.println(supplier.get());
        Predicate<Integer> predicate=(e)->{
            return e%2==0;
        };
        System.out.println("(Predicate)->>The value is even: "+predicate.test(5));
        Function<Integer,Integer> function=(e)->{
            return e*2;
        };
        System.out.println("(Function)->>The double of value: "+function.apply(4));



    }
}
