package com.bootcamp.springassignment.springFrameworkAssignment.LooselyCoupledQuestion2;
// Question 2)-Write a program to demonstrate Loosely Coupled code.
public class MainQuestion2 {
    public static void main(String[] args) {
        System.out.println("Loosely Coupled");
        new ManagerQuestion2(new LazyWorkerQuestion2()).manageWork();

    }
}
