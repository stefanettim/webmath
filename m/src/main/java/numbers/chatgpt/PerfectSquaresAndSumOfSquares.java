package numbers.chatgpt;

import java.util.HashSet;

public class PerfectSquaresAndSumOfSquares {
    public static void main(String[] args) {
        int limit = 100;

        // Part 1: Count of perfect squares
        int perfectSquareCount = 0;
        for (int i = 1; i * i < limit; i++) {
            perfectSquareCount++;
        }

        // Part 2: Count of numbers that can be expressed as the sum of two squares
        HashSet<Integer> numbersAsSumOfSquares = new HashSet<>();
        for (int i = 1; i * i < limit; i++) {
            for (int j = i; i * i + j * j < limit; j++) {
                int sum = i * i + j * j;
                numbersAsSumOfSquares.add(sum);
            }
        }

        // Numbers that are both perfect squares and sums of two squares
        int commonCount = 0;
        for (int i = 1; i * i < limit; i++) {
            int square = i * i;
            if (numbersAsSumOfSquares.contains(square)) {
                commonCount++;
            }
        }

        // Total count
        int totalCount = perfectSquareCount + numbersAsSumOfSquares.size() - commonCount;

        System.out.println("Total count: " + totalCount);
    }
}






