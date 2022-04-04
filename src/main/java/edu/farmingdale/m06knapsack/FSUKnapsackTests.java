/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m06knapsack;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author gerstl
 */
public class FSUKnapsackTests implements RunTest {
    // runs the tests from FSU (see the data folder for the source of the testfiles)
    public String runTest() {
        String testNames[] = {"p01", "p02", "p03","p04", "p05", "p06","p07" };
        for (var testName : testNames) {
            System.err.println("Starting processing test " + testName);
            var capacityFile = new File("data_files" + File.separator + testName + "_c.txt");
            var weightSizeFile = new File("data_files" + File.separator + testName + "_w.txt");
            var DollarProfitFile = new File("data_files" + File.separator + testName + "_p.txt");
            var answerFile = new File("data_files" + File.separator + testName + "_s.txt");
            String parent = capacityFile.getAbsoluteFile().getParent();
            System.out.println("Data files are in the directory : " + parent);
            // format is : (1) number of nodes, (2) number of edges, (3-end) from to 
            if (!capacityFile.exists() || !weightSizeFile.exists() || !DollarProfitFile.exists() || !answerFile.exists()) {
                return "E1001";
            }
            Scanner capacityScanner;
            Scanner weightSizeScanner;
            Scanner dollarProfitScanner;
            Scanner answerScanner;
            // get the capacity first, then the rest
            Scanner scanner;
            try {
                capacityScanner = new Scanner(capacityFile);
                weightSizeScanner = new Scanner(weightSizeFile);
                dollarProfitScanner = new Scanner(DollarProfitFile);
                answerScanner = new Scanner(answerFile);
            } catch (java.io.FileNotFoundException e) {
                System.err.println("At least one file for test " + testName + " is missing from directory " + parent);
                return "E1002";
            }
            Knapsack sack = new Knapsack();
            int itemNumber = 0;
            int correctTotalValue = 0;
            while (weightSizeScanner.hasNext() && dollarProfitScanner.hasNext() && answerScanner.hasNext()) {
                int weight = weightSizeScanner.nextInt();
                int profit = dollarProfitScanner.nextInt();
                sack.addItem("" + itemNumber, weight, profit);
                int included = answerScanner.nextInt();
                if (1 == included) {
                    correctTotalValue += profit;
                }
            }
            // read the size
            int capacity = capacityScanner.nextInt();
            Knapsack solution = sack.solve(capacity);
            if (solution.getTotalDollarValue()!= correctTotalValue) {
                System.err.println("Found value " + solution.getTotalDollarValue() + 
                        " but the problem says the correct solution is " + 
                        correctTotalValue +" (total size is "+solution.getTotalWeightSize()+")");
                System.err.println("\tI found " + solution); 
                
                return "E1003";
            }
        }
        return "";
    } // runTest()
}
