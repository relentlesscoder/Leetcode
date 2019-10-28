package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/19.
 * #1043 https://leetcode.com/problems/partition-array-for-maximum-sum/
 */
public class PartitionArrayForMaximumSum {
	// https://leetcode.com/problems/partition-array-for-maximum-sum/discuss/291057/Java-visualize-the-pattern
	public int maxSumAfterPartitioning(int[] A, int K) {
		int N = A.length;
		int[] dp = new int[N];
		for(int i = 0; i < N; i++){
			int currMax = 0;
			for(int k = 1; k <= K && i - k + 1 >= 0; k++){
				currMax = Math.max(currMax, A[i - k + 1]);
				dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + currMax * k);
			}
		}
		return dp[N - 1];
	}
}
