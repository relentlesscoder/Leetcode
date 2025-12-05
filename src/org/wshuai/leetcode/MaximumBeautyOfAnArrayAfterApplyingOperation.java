package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/30/2025.
 * #2779 https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/
 */
public class MaximumBeautyOfAnArrayAfterApplyingOperation {

    // time O(n * log(n)), space O(log(n))
    public int maximumBeauty(int[] nums, int k) {
        // https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation/solutions/2345805/pai-xu-shuang-zhi-zhen-by-endlesscheng-hbqx/
        int res = 0, n = nums.length;
        k <<= 1;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i++) {
            while (nums[i] - nums[j] > k) {
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
