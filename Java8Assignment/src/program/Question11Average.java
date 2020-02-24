//Find average of the number inside integer list after doubling it.
package program;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Question11Average {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4);
        System.out.println(
                list
                        .stream()
                        .collect(Collectors.averagingInt(e->e*2))
        );

    }
    }

