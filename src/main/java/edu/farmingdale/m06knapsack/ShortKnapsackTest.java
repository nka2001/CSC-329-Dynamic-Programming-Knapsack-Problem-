/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m06knapsack;


/**
 *
 * @author gerstl
 */
public class ShortKnapsackTest implements RunTest {

    public String runTest() {
        String rv = testOne();
        if ("" != rv) {
            return rv;
        }
        rv = testTwo();
        if ("" != rv) {
            return rv;
        }
       
        return "";
    }

    String testOne() {
        // this problem is from geeksforgeeks
        Knapsack sack = new Knapsack();
        sack.addItem("Item 0", 10, 60);
        sack.addItem("Item 1", 20, 100);
        sack.addItem("Item 2", 30, 120);
        Knapsack solution = sack.solve(50);
        System.err.println(solution);
        
        System.out.println(solution);
        if (solution.getTotalDollarValue() != 220) {
            System.err.println("Solution total weight is " + solution.getTotalDollarValue());
            return "Failed at A0001";
        }
        return "";
    }

    String testTwo() {
        // this problem is from lindo.com
        Knapsack sack = new Knapsack();
        sack.addItem("Ant Repelent", 1, 2);
        sack.addItem("Beer", 3, 9);
        sack.addItem("Blanket", 4, 3);
        sack.addItem("Bratwurst", 3, 8);
        sack.addItem("Brownies", 3, 10);
        sack.addItem("Frisbee", 1, 6);
        sack.addItem("Salad", 5, 4);
        sack.addItem("Watermellon", 10, 10);
        Knapsack solution = sack.solve(15);
        System.err.println(solution);
        if (solution.getTotalDollarValue() != 38) {
            System.err.println("weight is :" + solution.getTotalDollarValue());
            return "Failed at A0002";
        }
        return "";
    }
}

