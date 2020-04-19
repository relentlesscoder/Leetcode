package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 04/19/2020.
 * #1414 https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
 */
public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {
	public int findMinFibonacciNumbers(int k) {
		TreeSet<Integer> fibonacci = new TreeSet<>();
		int s1 = 1, s2 = 1;
		fibonacci.add(1);
		while(s1 + s2 <= k){
			int temp = s1 + s2;
			s1 = s2;
			s2 = temp;
			fibonacci.add(temp);
		}

		/* DP TLE
		int[] dp = new int[k + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int j = 1; j <= k; j++){
			for(int i = j; i >= 0; i--){
				if(!fibonacci.contains(i) || dp[j - i] == Integer.MAX_VALUE){
					continue;
				}
				dp[j] = Math.min(dp[j], 1 + dp[j - i]);
			}
		}
		return dp[k];
		*/
		int res = 0;
		while(k > 0){
			k -= fibonacci.floor(k);
			res++;
		}
		return res;
	}
}
