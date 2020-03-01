package com.im;

import healthycoderapp.BMICalculator;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {
    private First first;
    @BeforeEach
    void createObject(){
               this.first=new First();
    }


    @Test
    void should_Return_ReplaceSubstring(){
        //given
        String mainString="Hello Java",subString="Hello",replacementString="Bye";

        //when
        String result=first.replaceSubString(mainString,subString,replacementString);
        boolean recommended=result.equals(mainString);

        //then
        assertFalse(recommended);

    }


    @Test
    void should_Return_FilterEvenElements(){
        //given
        List<Integer> list=new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        //when
        List<Integer> newList=first.filterEvenElements(list);
        List<Integer> checkList=newList
                .stream()
                .filter(e->e%2==0)
                .collect(Collectors.toList());

        //then
        assertTrue(checkList.isEmpty());

    }


    @Test
    void sould_Return_Average(){
        //given
        List<BigDecimal> list=new ArrayList<>();
        list.add(new BigDecimal(7576575687.8767868));
        list.add(new BigDecimal(776787656.7897));
        list.add(new BigDecimal(998789.78678));
        list.add(new BigDecimal(776678.90890));

        //when
        BigDecimal bd=first.calculateAverage(list);
        boolean result=bd instanceof BigDecimal;

        //then
        assertTrue(result);
    }

    @Test
    void sould_Return_Average_InvalidInput() {
        //given
        List<BigDecimal> list=new ArrayList<>();
        //when
        Executable executable=()-> first.calculateAverage(list);
        //then
        assertThrows(RuntimeException.class,executable);
    }

    @Test
    void should_Check_IsPalindrome(){
        //given
        String test1="NAMAN";
        String test2="nitins";

        //when
        Boolean isPalin1=first.isPallindrome(test1);
        Boolean isPalin2=first.isPallindrome(test2);


        //then
        assertAll(
                ()-> assertTrue(isPalin1),
                ()-> assertTrue(isPalin2)
        );
    }
}