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

        int[] w = new int[items.size()];//i make two arrays, one for weight and one for value, helped me solve easier
        int[] v = new int[items.size()];

        for (int i = 0; i < items.size(); i++) {//fill those arrays 
            w[i] = items.get(i).weightSize;
            v[i] = items.get(i).dollarValue;
        }

        int K[][] = new int[items.size() + 1][maxWeight + 1];//create a 2d array, building bottom up made this come to mind

        for (int numItems = 0; numItems <= items.size(); numItems++) {//go through number of items
            for (int weigth = 0; weigth <= maxWeight; weigth++) {//go through weight
                if (numItems == 0 || weigth == 0) {//this is for 0 row, since 0 weight means 0 value 
                    K[numItems][weigth] = 0;//sets the slot to 0 if the number of items is 0
                } else if (w[numItems - 1] <= weigth) {//if the left is smaller
                    K[numItems][weigth] = max(v[numItems - 1] + K[numItems - 1][weigth - w[numItems - 1]], K[numItems - 1][weigth]);//finds the bigger of the two then sets the slot to that value

                } else {
                    K[numItems][weigth] = K[numItems - 1][weigth];//or just set the value to whatever is immedately left of the slot
                }
            }
        }

        int startHere = K[items.size()][maxWeight];//start at the top right of the 2d array
        int weight = maxWeight;//temp weight, it changes as we add items to knapsack

        for (int i = items.size(); i > 0 && startHere > 0; i--) {
            if (K[i - 1][weight] != startHere) {//this is how to add to knap sack, if a cell and the cell to the left are not equal, then the item is added
                rv.addItem(items.get(i - 1).name, items.get(i - 1).weightSize, items.get(i - 1).dollarValue);

                startHere = startHere - v[i - 1];//adjust values since a knapsack now has a value in it 
                weight = weight - w[i - 1];//adjust weight since is something in the knapsack now 
            }

        }

        return rv;
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }

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
