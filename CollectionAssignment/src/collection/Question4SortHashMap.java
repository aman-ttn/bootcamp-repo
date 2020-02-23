//Write a program to sort HashMap by value.
package collection;

import java.util.*;

public class Question4SortHashMap {
    public static void main(String[] args) {
        HashMap<String,Integer> hashMap=new HashMap<String,Integer>();
        hashMap.put("Ankit",12);
        hashMap.put("Aman",9);
        hashMap.put("Aradhya",76);
        hashMap.put("Vinay",6);
        hashMap.put("Parul",4);

        List<Map.Entry<String,Integer>> l1=new LinkedList<Map.Entry<String, Integer>>(hashMap.entrySet()) ;
        Collections.sort(l1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> t1, Map.Entry<String, Integer> t2) {
                return (t1.getValue().compareTo(t2.getValue()));
            }
        });
        HashMap<String ,Integer> newhashmap=new LinkedHashMap<String, Integer>();
        for (Map.Entry<String,Integer> entry:l1) {
            newhashmap.put(entry.getKey(),entry.getValue());
        }
        for (Map.Entry hm:newhashmap.entrySet()) {
            System.out.println("Key = "+hm.getKey()+" "+"|| Value = "+hm.getValue());
        }
    }
}
