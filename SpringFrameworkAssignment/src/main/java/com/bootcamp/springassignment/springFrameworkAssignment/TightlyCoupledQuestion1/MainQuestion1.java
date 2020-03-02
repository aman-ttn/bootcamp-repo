package com.bootcamp.springassignment.springFrameworkAssignment.TightlyCoupledQuestion1;
//Question 1)-Write a program to demonstrate Tightly Coupled code.
public class MainQuestion1 {
    public static void main(String[] args) {
        System.out.println("Tightly Coupled------");
        new ManagerQuestion1(new LazyWorkerQuestion1(),new ExcellentWorkerQuestion1()).manageWork();
    }
}
