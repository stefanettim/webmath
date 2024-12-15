package numbers.chatgpt;

import java.util.HashSet;

public class PerfectSquaresAndSumOfSquaresOr3 {
    public static void main(String[] args) {
        int limit = 10000;

        // Part 1: Count of perfect squares
        int perfectSquareCount = 0;
        for (int i = 1; i * i < limit; i++) {
            perfectSquareCount++;
        }

        // Part 2: Count of numbers that can be expressed as the sum of two squares
        HashSet<Integer> numbersAsSumOfTwoSquares = new HashSet<>();
        for (int i = 1; i * i < limit; i++) {
            for (int j = i; i * i + j * j < limit; j++) {
                int sum = i * i + j * j;
                numbersAsSumOfTwoSquares.add(sum);
            }
        }

        // Part 3: Count of numbers that can be expressed as the sum of three squares
        HashSet<Integer> numbersAsSumOfThreeSquares = new HashSet<>();
        for (int i = 1; i * i < limit; i++) {
            for (int j = i; i * i + j * j < limit; j++) {
                for (int k = j; i * i + j * j + k * k < limit; k++) {
                    int sum = i * i + j * j + k * k;
                    numbersAsSumOfThreeSquares.add(sum);
                }
            }
        }

        // Numbers that are both perfect squares and sums of two or three squares
        int commonCount = 0;
        for (int i = 1; i * i < limit; i++) {
            int square = i * i;
            if (numbersAsSumOfTwoSquares.contains(square) || numbersAsSumOfThreeSquares.contains(square)) {
                commonCount++;
            }
        }

        // Total count
        int totalCount = perfectSquareCount + numbersAsSumOfTwoSquares.size() + numbersAsSumOfThreeSquares.size() - commonCount;

        System.out.println("Total count: " + totalCount);
    }
}
