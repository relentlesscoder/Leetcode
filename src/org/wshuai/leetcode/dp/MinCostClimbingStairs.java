package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 08/21/2019.
 * #0746 https://leetcode.com/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbingStairs {

	// time O(n), space O(1)
	public int minCostClimbingStairs(int[] cost) {
		// 空间优化版，用两个变量存 i - 1 和 i - 2 的结果
		int n = cost.length, pre = 0, res = 0;
		for (int i = 2; i <= n; i++) {
			int curr = Math.min(pre + cost[i - 2], res + cost[i - 1]);
			pre = res;
			res = curr;
		}
		return res;
	}

	// time O(n), space O(n)
	public int minCostClimbingStairsDPWithArray(int[] cost) {
		int n = cost.length;
		int[] dp = new int[n + 1];
		for (int i = 2; i <= n; i++) { // 注意是要到楼顶所以最后要处理 n
			// 注意题意是付了该位置的钱就能往上走
			dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
		}
		return dp[n];
	}
}
