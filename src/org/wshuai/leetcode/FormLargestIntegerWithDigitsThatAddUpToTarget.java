package org.wshuai.leetcode;

/**
 * Created by Wei on 05/17/2020.
 * #1449 https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/
 */
public class FormLargestIntegerWithDigitsThatAddUpToTarget {

	// time O(target), space O(target)
	public String largestNumber(int[] cost, int target) {
		int[] dp = new int[target + 1];
		for(int i = 1; i <= target; i++){
			dp[i] = Integer.MIN_VALUE;
			for(int j = 0; j < cost.length; j++){
				if(i >= cost[j]){
					dp[i] = Math.max(dp[i], dp[i - cost[j]] + 1);
				}
			}
		}
		if(dp[target] < 0){
			return "0";
		}
		StringBuilder res = new StringBuilder();
		for(int i = 8; i >= 0; i--){
			while(target >= cost[i] && dp[target] == dp[target - cost[i]] + 1){
				res.append(i + 1);
				target -= cost[i];
			}
		}
		return res.toString();
	}
}
