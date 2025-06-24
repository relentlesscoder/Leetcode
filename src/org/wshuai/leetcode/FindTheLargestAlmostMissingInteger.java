package org.wshuai.leetcode;

/**
 * Created by Wei on 06/23/2025.
 * #3471 https://leetcode.com/problems/find-the-largest-almost-missing-integer/
 */
public class FindTheLargestAlmostMissingInteger {

    // time O(n), space O(1)
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            return findLargestDistinctInteger(nums);
        } else if (k == n) {
            return findLargestInteger(nums);
        } else if (nums[0] == nums[n - 1]) {
            return -1;
        }
        int[] res = new int[]{nums[0], nums[n - 1]};
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] == res[0]) {
                res[0] = -1;
            }
            if (nums[i] == res[1]) {
                res[1] = -1;
            }
        }
        return Math.max(res[0], res[1]);
    }

    private int findLargestDistinctInteger(int[] nums) {
        int[] freq = new int[51];
        for (int num : nums) {
            freq[num]++;
        }
        for (int i = 50; i >= 0; i--) {
            if (freq[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    private int findLargestInteger(int[] nums) {
        int res = -1;
        for (int num : nums) {
            res = Math.max(res, num);
        }
        return res;
    }
}
