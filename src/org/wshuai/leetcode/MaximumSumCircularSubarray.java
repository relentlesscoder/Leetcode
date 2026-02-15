package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #0918 https://leetcode.com/problems/maximum-sum-circular-subarray/
 */
public class MaximumSumCircularSubarray {

    // time O(n), space O(1)
    public int maxSubarraySumCircular(int[] nums) {
		// https://leetcode.cn/problems/maximum-sum-circular-subarray/solutions/2351107/mei-you-si-lu-yi-zhang-tu-miao-dong-pyth-ilqh/
		// 两种情况:
		//   1. 最大子数组在数组中间
		//   2. 最大子数组由前缀和后缀拼接而成 - 用数组元素和减去中间最小子数组和
        int sum = 0, // 数组元素和
				max = Integer.MIN_VALUE, // 数组子数组最大和 (不能为空 - 初始化值为最小的整型值)
				maxSum = 0, // 计算最大子数组和的 DP 数组（空间优化成一个变量）
				min = 0, // 数组子数组最大和 (可为空 - 初始化值为 0)
				minSum = 0; // 计算最小子数组和的 DP 数组（空间优化成一个变量）
        for (int num : nums) {
            maxSum = Math.max(maxSum + num, num);
            max = Math.max(max, maxSum);
            minSum = Math.min(minSum + num, num);
            min = Math.min(min, minSum);
            sum += num;
        }
        return max < 0 ? max : Math.max(max, sum - min);
    }
}
