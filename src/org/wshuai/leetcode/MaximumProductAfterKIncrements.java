package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2026.
 * #2233 https://leetcode.com/problems/maximum-product-after-k-increments/
 */
public class MaximumProductAfterKIncrements {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n + k * log(n)), space O(1)
    public int maximumProduct(int[] nums, int k) {
        long res = 1L;
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(nums, i);
        }
        while (k-- > 0) {
            nums[0]++;
            sink(nums, 0);
        }
        for (int num : nums) {
            res = res * num % MOD;
        }
        return (int) res;
    }

    private void sink(int[] nums, int i) {
        int n = nums.length;
        while (2L * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1] < nums[j]) {
                j++;
            }
            if (nums[j] >= nums[i]) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }
}
