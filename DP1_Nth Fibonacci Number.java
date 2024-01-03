//https://www.codingninjas.com/studio/problems/nth-fibonacci-number_74156?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1. recursion
import java.util.*;
public class Solution {


	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
		*/

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int res = Fibonacci(n);
		System.out.println(res);
	}

	private static int Fibonacci(int n)
	{
		if(n <= 1)
			return n;
		return Fibonacci(n - 1) + Fibonacci(n - 2);
	}
}
//2. memorization
import java.util.*;
public class Solution {


	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
		*/

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int [] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		int res = Fibonacci(dp, n);
		System.out.println(res);
	}

	private static int Fibonacci(int[] dp, int n)
	{
		if(n <= 1)
			return n;
		if(dp[n] != -1)
			return dp[n];
		return dp[n] = Fibonacci(dp, n - 1) + Fibonacci(dp, n - 2);
	}
}
//3.Tabulation

import java.util.*;
public class Solution {


	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
		*/

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n <= 1)
		{
			System.out.print(n);
			return;
		}
		int [] dp = new int[n + 1];
		dp[1] = 1;
		for(int i = 2; i <= n; i++)
		{
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		System.out.print(dp[n]);
	}

}
//4.space optimization

import java.util.*;
public class Solution {


	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
		*/

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n <= 1)
		{
			System.out.print(n);
			return;
		}
		int fib1 = 0;
		int fib2 = 1;
		for(int i = 2; i <= n; i++)
		{
			int fib3 = fib1 + fib2;
			fib1 = fib2;
			fib2 = fib3;
		}
		System.out.println(fib2);
	}

}
