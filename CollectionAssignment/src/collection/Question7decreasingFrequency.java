//Print the elements of an array in the decreasing frequency if 2 numbers have same frequency then print the one which came first.
package collection;

import java.util.*;

public class Question7decreasingFrequency {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(4);
        list.add(5);
        list.add(5);
        HashMap<Integer,Integer> linkedhashMap=new LinkedHashMap<Integer, Integer>();
        for(int i=list.size()-1;i>-1;i--){
            linkedhashMap.put(Collections.frequency(list,list.get(i)),list.get(i));
        }
        Map<Integer, Integer> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());
        reverseSortedMap.putAll(linkedhashMap);
        for (Map.Entry<Integer, Integer> val : reverseSortedMap.entrySet()) {
            System.out.println(val.getValue()+" occurs "+val.getKey()+" times");
        }



        }
}

