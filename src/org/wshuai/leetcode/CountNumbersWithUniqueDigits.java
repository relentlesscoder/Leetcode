package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0357 https://leetcode.com/problems/count-numbers-with-unique-digits/
 */
public class CountNumbersWithUniqueDigits {
	// time O(n)
	public int countNumbersWithUniqueDigits(int n) {
		if(n == 0){
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[1] = 9;
		int res = 10;
		for(int i = 2; i <= n; i++){
			// dp[i - 1] is the total count of numbers
			// with i - 1 unique digits. we can choose one
			// from (10 - (i - 1)) to form number with
			// unique i digits.
			dp[i] = dp[i - 1] * (10 - i + 1);
			res += dp[i];
		}
		return res;
	}
}
