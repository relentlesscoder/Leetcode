package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #2495 https://leetcode.com/problems/number-of-subarrays-having-even-product/
 */
public class NumberOfSubarraysHavingEvenProduct {

    // time O(n), space O(1)
    public long evenProductPreprocessing(int[] nums) {
        long res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] &= 1;
        }
        for (int i = 0, j = 0, even = 0; i < n; i++) {
            even += 1 - nums[i];
            while (even > 0) {
                even -= 1 - nums[j++];
            }
            res += j;
        }
        return res;
    }

    // time O(n), space O(1)
    public long evenProduct(int[] nums) {
        long res = 0;
        int n = nums.length;
        for (int i = 0, j = 0, even = 0; i < n; i++) {
            even += 1 - (nums[i] & 1);
            while (even > 0) {
                even -= 1 - (nums[j++] & 1);
            }
            res += j;
        }
        return res;
    }
}
