package numbers.chatgpt;

import java.util.HashSet;

public class PerfectSquaresAndSumOfSquaresOr3Fixed {
    public static void main(String[] args) {
        int limit = 10000;

        // Count of numbers that can be expressed as the sum of three squares
        HashSet<Integer> numbersAsSumOfThreeSquares = new HashSet<>();
        for (int i = 0; i * i < limit; i++) {
            for (int j = i; i * i + j * j < limit; j++) {
                for (int k = j; i * i + j * j + k * k < limit; k++) {
                    int sum = i * i + j * j + k * k;
                    if(sum>0) {
                    	numbersAsSumOfThreeSquares.add(sum);
                    }
                }
            }
        }

        // Total count
        int totalCount = numbersAsSumOfThreeSquares.size() ;

        System.out.println("Total count: " + totalCount);
    }
}
