import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**

 You are working for a promising new stock trading startup “Algo-Street”.

 You have been tasked with developing a new trading signal that will be incorporated into the automatic trading strategy.
 The new metric has been called “stock pressure” which aims to measure the tendency for stocks to regress to previous prices.

 The positive stock pressure measures:
 how many consecutive days before today (not including today) have a higher price.

 The negative stock pressure measures:
 how many consecutive days before today (not including today) have a lower price.
 Notice that only one of the two should be greater than 1.

 The Stock Pressure is the positive stock pressure - the negative stock pressure.

 So if the positive stock pressure is 0 and the negative stock pressure is 3 the stock pressure is -3.

 You will be given daily stock prices for the last N days and must return the list of daily stock pressures for each day.
 Below is an example for 1 week of data.

 |   Price   | +Pressure | -Pressure |   Pressure  |
 -----------------------------------------------------------------
 | Day 1 |   100     |     1     |     1     |    0      |
 -----------------------------------------------------------------
 | Day 2 |   90      |     2     |     1     |    1      |
 -----------------------------------------------------------------
 | Day 3 |   95      |     1     |     2     |   -1      |
 -----------------------------------------------------------------
 | Day 4 |   100     |     1     |     4     |   -3      |
 -----------------------------------------------------------------
 | Day 5 |   105     |     1     |     5     |   -4      |
 -----------------------------------------------------------------
 | Day 6 |   110     |     1     |     6     |   -5      |
 -----------------------------------------------------------------
 | Day 7 |   80      |     7     |     1     |    6      |
 -----------------------------------------------------------------

 Implement the function to compute stock pressure.

 In order to compute positive/negative stock pressure for each stock price
 you must find the last day with a lower/higher price

 You must use two stacks to solve the problem in O(N) time

 Code Author: <Hal Jones>
 */


public class AlgoStreet {
    public static List<Integer> computePressure(List<Integer> stockHistory) {
       	
    	Stack <Integer> positivePressure = new Stack<Integer>();
    	Stack <Integer> negativePressure = new Stack<Integer>();
    	int historySize = stockHistory.size();
    	
    	List<Integer> computedPressureList = new ArrayList<Integer>();
    	
    	//The positive stock pressure measures: how many consecutive days before today (not including today) have a higher price.
    
    	for (int i = 0; i < historySize; i++) { 		
    		int currentPositivePressure;
    		if (positivePressure.isEmpty()) {
    				currentPositivePressure = i;
    		} else {
    				currentPositivePressure = i - (positivePressure.peek() - 1);
    			}
    		positivePressure.push(i);
 
    	//The negative stock pressure measures: how many consecutive days before today (not including today) have a lower price.

    		int currentNegativePressure;
    		if (negativePressure.isEmpty()) {
    				currentNegativePressure = i;
    		} else {
    				currentNegativePressure = i - (negativePressure.peek() - 1);
    			}
    		negativePressure.push(i);
    	
    	//The Stock Pressure is the positive stock pressure - the negative stock pressure.
    	
    	int computePressureList = currentPositivePressure - currentNegativePressure;
    	
		computedPressureList.add(computePressureList);
	}
	return computedPressureList;
}

    /*
    DO NOT EDIT BELOW THIS
    Below is the unit testing suite for this file.
    It provides all the tests that your code must pass to get full credit.
    */
    public static void runUnitTests() {
        testExample();
        test2();
        test3();
        testNoDaysProvided();
        testLargeList();
    }

    public static void printTestResult(String testName, boolean result) {
        String color = result ? "\033[92m" : "\033[91m";
        String reset = "\033[0m";
        System.out.println(color + "[" + result + "] " + testName + reset);
    }

    public static void testAnswer(String testName, List<Integer> result, List<Integer> expected) {
        if (expected.equals(result)) {
            printTestResult(testName, true);
        } else {
            printTestResult(testName, false);
            System.out.println("Expected: " + expected);
            System.out.println("Got:      " + result);
        }
    }

    public static void testExample() {
        List<Integer> stockHistory = Arrays.asList(100, 90, 95, 100, 105, 110, 80);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(0, 1, -1, -3, -4, -5, 6);

        testAnswer("testExample", result, expectedAnswer);
    }

    public static void test2() {
        List<Integer> stockHistory = Arrays.asList(80, 74, 75, 90, 120, 81);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(0, 1, -1, -3, -4, 2);

        testAnswer("test2", result, expectedAnswer);
    }

    public static void test3() {
        List<Integer> stockHistory = Arrays.asList(1, 2, 5, 10, 12, 20);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(0, -1, -2, -3, -4, -5);

        testAnswer("test3", result, expectedAnswer);
    }

    public static void testNoDaysProvided() {
        List<Integer> stockHistory = new ArrayList<>();

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = new ArrayList<>();

        testAnswer("testNoDaysProvided", result, expectedAnswer);
    }

    public static void testLargeList() {
        List<Integer> stockHistory = Arrays.asList(100, 90, 80, 85, 90, 95, 100, 105, 110, 120, 140, 120, 100, 80);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(0, 1, 2, -1, -3, -4, -6, -7, -8, -9, -10, 2, 6, 13);

        testAnswer("testLargeList", result, expectedAnswer);
    }

    public static void main(String[] args) {
        AlgoStreet.runUnitTests();
    }

}
