package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 11/10/2019.
 * #1191 https://leetcode.com/problems/k-concatenation-maximum-sum/
 */
public class KConcatenationMaximumSum {
	private static final int MOD = (int) 1e9 + 7;

	// time O(n), space O(1)
	public int kConcatenationMaxSum(int[] arr, int k) {
		// 题解: https://www.youtube.com/watch?v=-T19A8DvD6U
		// 两种情况:
		//   1. 数组元素和小于等于 0 - 要么最大子数组在数组中或者由前缀和后缀拼接而成。可以直接计算
		//   循环两次的最大子数组。
		//   2. 数组元素和大于 0 - 重复两次的最大子数字和一定是数组前缀和后缀拼接而成
		//   (证明 https://leetcode.cn/problems/k-concatenation-maximum-sum/solutions/3675237/fu-yong-53-ti-dai-ma-jian-ji-xie-fa-pyth-qmtp/)。
		//   再加上中间 k - 2 个数组元素和即为循环 k 次的最大子数组和。
		if (k == 1) { // 特殊情况 k = 1 直接求解
			return maxSubarray(arr, 1);
		}
		// 求出循环两次的最大子数组和
		long res = maxSubarray(arr, 2);
		int s = 0;
		for (int x : arr) {
			s += x;
		}
		// 数组和大于 0 再加上 k - 2 个数组元素和。
		res += (long) Math.max(s, 0) * (k - 2);
		return (int) (res % MOD);
	}

	private int maxSubarray(int[] arr, int k) {
		// #0053
		int res = 0, n = arr.length, maxSum = 0;
		for (int i = 0; i < n * k; i++) {
			maxSum = Math.max(maxSum + arr[i % n], arr[i % n]);
			res = Math.max(res, maxSum);
		}
		return res;
	}
}
