package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0096 https://leetcode.com/problems/unique-binary-search-trees/
 */
public class UniqueBinarySearchTrees {
	// time O(n^2), space O(n)
	// https://leetcode.com/problems/unique-binary-search-trees/discuss/31707/Fantastic-Clean-Java-DP-Solution-with-Detail-Explaination
	public int numTrees(int n) {
		if(n == 0){
			return 0;
		}
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		for(int i = 2; i <= n; i++){
			for(int j = 1; j <= i; j++){
				dp[i] += dp[j - 1] * dp[i - j];
			}
		}
		return dp[n];
	}
}
