package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/27/2017.
 * #0376 https://leetcode.com/problems/wiggle-subsequence/
 */
public class WiggleSubsequence {

	// time O(n), space O(1)
	public int wiggleMaxLengthGreedy(int[] nums) {
		// 核心思路: 状态机DP/贪心, 两个状态: 最后一步上升(up)和最后一步下降(down)
		// 上升时从down+1转移, 下降时从up+1转移, 相等则不动
		// up: 最后一步为上升的最长摆动子序列长度, down: 最后一步为下降的长度
		int up = 1, down = 1, n = nums.length;
		for (int i = 1; i < n; i++) {
			if (nums[i] > nums[i - 1]) {
				// 上升: 在一个"最后下降"的子序列后追加上升步
				up = Math.max(up, down + 1);
			} else if (nums[i] < nums[i - 1]) {
				// 下降: 在一个"最后上升"的子序列后追加下降步
				down = Math.max(down, up + 1);
			}
			// 相等: 不构成摆动, 跳过
		}
		return Math.max(up, down);
	}

	// time O(n^2), space O(n)
	public int wiggleMaxLengthDP(int[] nums) {
		// 核心思路: 经典DP, 枚举所有前驱j, 根据nums[i]和nums[j]的大小关系转移
		// dp[i][0]: 以第i个元素结尾且最后一步上升的最长摆动长度
		// dp[i][1]: 以第i个元素结尾且最后一步下降的最长摆动长度
		int res = 0, n = nums.length;
		int[][] dp = new int[n][2];
		for (int[] row : dp) {
			Arrays.fill(row, 1); // 单个元素长度为1
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					// i比j大 = 上升步, 从j的下降状态+1转移
					dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
				} else if (nums[i] < nums[j]) {
					// i比j小 = 下降步, 从j的上升状态+1转移
					dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
				}
			}
			// 子序列模式, 但dp定义是"以i结尾", 最优解可能在任何位置
			res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
		}
		return res;
	}
}
