package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 10/30/2016.
 * #0213 https://leetcode.com/problems/house-robber-ii/
 */
public class HouseRobberII {

	// time O(n), space O(1)
	public int rob(int[] nums) {
		// 讨论是否偷 nums[0]：
		//   1. 如果偷 nums[0]，那么 nums[1] 和 nums[n − 1] 不能偷，问题变成从 nums[2] 到 nums[n − 2]
		//   的非环形版本，调用 198 题的代码解决。
		//   2. 如果不偷 nums[0]，那么问题变成从 nums[1] 到 nums[n − 1] 的非环形版本，同样调用 #0198 题
		//   的代码解决。
		int n = nums.length;
		return Math.max(nums[0] + rob1(nums, 2, n - 1), rob1(nums, 1, n));
	}

	public int rob1(int[] nums, int start, int end) {
		// 因为 dp[i] 只与 dp[i - 1] 和 dp[i - 2] 有关，我们可以优化为使用两个变量来存 i 前面
		// 两个值。
		int h1 = 0, // 上一个房子
				h2 = 0; // 上上个房子
		for (int i = start; i < end; i++) {
			int h = Math.max(h1, h2 + nums[i]); // 计算到当前房子的最大收益
			h2 = h1; // 将上上个房子的收益更新为上个房子的收益
			h1 = h; // 将上一个房子的收益更新为到当前房子的收益
		}
		return h1;
	}
}
