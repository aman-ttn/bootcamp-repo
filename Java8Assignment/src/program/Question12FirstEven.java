//Find the first even number in the integer list which is greater than 3.
package program;

import java.util.Arrays;
import java.util.List;

public class Question12FirstEven {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(
                list
                        .stream()
                        .filter(e->e>3)
                        .filter(e->e%2==0)
                        .findFirst());

    }
}
