package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #1749 https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 */
public class MaximumAbsoluteSumOfAnySubarray {

    // time O(n), space O(1)
    public int maxAbsoluteSumDP(int[] nums) {
        int res = 0, max = 0, min = 0;
        for (int num : nums) {
            // 最大子数组要么延续前面的数组，要么从当前元素开始
            max = Math.max(max + num, num);
            min = Math.min(min + num, num);
            // 因为是求最大绝对值所以取max和-min的最大值
            res = Math.max(res, Math.max(max, -min));
        }
        return res;
    }

    // time O(n), space O(1)
    public int maxAbsoluteSumPrefixSum(int[] nums) {
        int sum = 0, max = 0, min = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
		// 最大值是最大前缀和减最小前缀和或者最小前缀和减最大前缀和
		// 的绝对值。
		// 如果最大前缀和在最小前缀和的后面，则答案为max - min，
		// 反之则答案为-(min - max) = max - min。
        return max - min;
    }
}
