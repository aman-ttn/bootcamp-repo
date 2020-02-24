//Sum all the numbers greater than 5 in the integer list.
package program;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question10Sum {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(
                list
                        .stream()
                        .filter(e->e>5)
                        .mapToInt(Integer::intValue)
                        .sum()
        );

    }
    }

