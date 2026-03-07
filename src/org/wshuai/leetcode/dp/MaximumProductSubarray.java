package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 11/27/2019.
 * #0152 https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {

    // time O(n), space O(1)
    public int maxProduct(int[] nums) {
		// #0053相似题，不同的是需要考虑负数的情况。负数与最小值的乘积有可能变成最大值 (反之亦然)。
        int res = Integer.MIN_VALUE,
				max = 1, // 当前索引结尾的子数组的最大乘积
				min = 1, // 当前索引结尾的子数组的最小乘积
				n = nums.length;
        for (int i = 0; i < n; i++) {
            int mx = max; // 先拷贝一份 max
			// 找到以当前元素结尾的子数组的最大乘积需要考虑 max 与 nums[i] 的乘积, min 与 nums[i]
			// 的乘积 和 nums[i] 自己这三种情况
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
			// 找到以当前元素结尾的子数组的最小乘积需要考虑 max 与 nums[i] 的乘积, min 与 nums[i]
			// 的乘积 和 nums[i] 自己这三种情况
            min = Math.min(Math.min(min * nums[i], mx * nums[i]), nums[i]);
			// 更新答案
            res = Math.max(res, max);
        }
        return res;
    }
}
