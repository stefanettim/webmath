package numbers.chatgpt;

public class numberOfWaysPowerOf2 {

 // Function to count the number of ways to represent
 // n as the power of 2
 static int numberOfWays(int n)
 {
     // Initialize an array dp with size n+1
     int dp[] = new int[n + 1];

     // Base case- there is only 1 way to represent 0
     // as a sum of powers of 2
     dp[0] = 1;

     // Iterate all powers of 2 starting from 1
     for (int i = 1; i <= n; i = i * 2) {
         // Iterate through all numbers from 1 to n
         for (int j = i; j <= n; j++) {
             // Using sub-problems that is already
             // calculated to find the number of ways to
             // represent j as a sum of powers of 2
             dp[j] += dp[j - i];
         }
     }

     // Return the number of ways to represent
     // n as a sum of powers of 2
     return dp[n];
 }

 // Drive code
 public static void main(String[] args)
 {
     int n = 6;

     // Function call
     System.out.println("Number of ways: "
                        + numberOfWays(n));
 }
}