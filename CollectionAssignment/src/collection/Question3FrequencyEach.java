//Write a method that takes a string and print the number of occurrence of each character characters in the string.
package collection;

import java.util.*;

public class Question3FrequencyEach {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the string");
        String str = scanner.nextLine();
        char ch[] = str.toCharArray();
        freqEach(ch);
    }
        public static void freqEach(char[] ch){
            HashMap<Character,Integer> hm=new HashMap<>();
            for (char c:ch) {
                if(hm.containsKey(c)){
                    hm.put(c,hm.get(c)+1);
                }
                else{
                    hm.put(c,1);
                }
            }
            for (Map.Entry<Character, Integer> characterIntegerEntry : hm.entrySet()) {
                System.out.println(characterIntegerEntry.getKey()+" : "+characterIntegerEntry.getValue());
            }
        }

    }

