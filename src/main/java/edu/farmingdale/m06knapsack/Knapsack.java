/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m06knapsack;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author gerstl
 */
public class Knapsack {

    public class Item {

        String name;
        int weightSize;
        int dollarValue;

        public Item(String name, int weightSize, int dollarValue) {
            this.name = name;
            this.weightSize = weightSize;
            this.dollarValue = dollarValue;
        }
    } // class Item
    ArrayList<Item> items = new ArrayList<Item>();

    public void addItem(String name, int size, int value) {
        items.add(new Item(name, size, value));
    }

    public int getTotalDollarValue() {
        int rv = 0;
        for (int i = 0; i < items.size(); ++i) {
            rv += items.get(i).dollarValue;
        }
        return rv;
    }

    public int getTotalWeightSize() {
        int rv = 0;
        for (int i = 0; i < items.size(); ++i) {
            rv += items.get(i).weightSize;
        }
        return rv;
    }

    /**
     *
     * @param maxWeight integer denoting the maximum weight capacity of the
     * knapsack
     * @return another Knapsack, containing only the items from this object that
     * form the highest value solution with total size/weight <= maxWeight
     */
    public Knapsack solve(int maxWeight) {
        Knapsack rv = new Knapsack();

        // this is the part you write. Come to class for the clues as to how
        int[] w = new int[items.size()];
        int[] v = new int[items.size()];

        for (int i = 0; i < items.size(); i++) {
            w[i] = items.get(i).weightSize;
            v[i] = items.get(i).dollarValue;
        }

        int K[][] = new int[items.size() + 1][maxWeight + 1];

        for (int i = 0; i <= items.size(); i++) {
            for (int j = 0; j <= maxWeight; j++) {
                if (i == 0 || j == 0) {
                    K[i][j] = 0;
                } else if (w[i - 1] <= j) {
                    K[i][j] = max(v[i - 1] + K[i - 1][j - w[i - 1]], K[i - 1][j]);
                    
                } else {
                    K[i][j] = K[i - 1][j];
                }
            }
        }
        
        
       
        
        int max = K[items.size()][maxWeight];
        rv.addItem("Best Items", getTotalDollarValue(), max);
       // rv.addItem("test", getTotalDollarValue(),);//THIS IS WHAT NEEDS TO HAPPEN!!!!
        return rv;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer();
        int count = 0;
        Iterator<Item> iter = items.iterator();
        while (iter.hasNext()) {
            Item thisItem = iter.next();
            buff.append("[Element " + thisItem.name);
            buff.append(" Weight/Size " + thisItem.weightSize);
            buff.append(" Dollar/Value " + thisItem.dollarValue + "] ");
        }
        return buff.toString();
    }

}
