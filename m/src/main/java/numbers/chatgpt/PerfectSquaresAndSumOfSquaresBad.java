package numbers.chatgpt;

import java.util.HashSet;

public class PerfectSquaresAndSumOfSquaresBad {
	
    public static void main(String[] args) {
        int limit = 100;
        
        HashSet<Integer> perfectSquares = new HashSet<>();
        
        // Part 1: Count of perfect squares
        int perfectSquareCount = 0;
        for (int i = 1; i * i < limit; i++) {
        	perfectSquares.add(i*i);
            perfectSquareCount++;
        }
        
        // Part 2: Count of numbers that can be expressed as the sum of two squares
        HashSet<Integer> numbersAsSumOfSquares = new HashSet<>();
        for (int i = 1; i * i < limit; i++) {
            for (int j = 1; j * j < limit; j++) {
                int sum = i * i + j * j;
                if (sum < limit) {
                    numbersAsSumOfSquares.add(sum);
                }
            }
        }
        
        // Count the numbers that can be expressed as the sum of two squares
        int sumOfSquaresCount = numbersAsSumOfSquares.size();
        
        // Total count
        int totalCount = perfectSquareCount + sumOfSquaresCount;
        
        System.out.println(numbersAsSumOfSquares);
        System.out.println("Total count: " + totalCount);
        
        HashSet<Integer> intersection = new HashSet<Integer>(numbersAsSumOfSquares); // use the copy constructor
        intersection.retainAll(perfectSquares);
        System.out.println("intersection:"+intersection.size());
        System.out.println(intersection);
    }
}