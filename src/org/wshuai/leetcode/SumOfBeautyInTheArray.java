package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2012 https://leetcode.com/problems/sum-of-beauty-in-the-array/
 */
public class SumOfBeautyInTheArray {

    // time O(n), space O(n)
    public int sumOfBeauties(int[] nums) {
        int res = 0, n = nums.length, min = nums[n - 1], max = nums[0];
        int[] rightMin = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = min;
            min = Math.min(min, nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > max && nums[i] < rightMin[i]) {
                res += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                res++;
            }
            max = Math.max(max, nums[i]);
        }
        return res;
    }
}
