//Write Java code to define List .
// Insert 5 floating point numbers in List, and using an iterator,
// find the sum of the numbers in List.
package collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Question1ListSum {
    public static void main(String[] args) {

        List<Float> list = new ArrayList<Float>();
        list.add(4.5f);
        list.add(7.9f);
        list.add(2.3f);
        list.add(9.5f);
        list.add(1099.3f);
        System.out.println("Elements of list are"+list);
        Iterator iterator = list.iterator();
        float sum=0.f;
        while (iterator.hasNext())
            sum=+(float)iterator.next();
        System.out.println("The sum : "+sum);
    }

}

