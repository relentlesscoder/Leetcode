package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2025.
 * #1995 https://leetcode.com/problems/count-special-quadruplets/
 */
public class CountSpecialQuadruplets {

    // time O(n^2), space O(n)
    public int countQuadruplets(int[] nums) {
        int res = 0, n = nums.length;
        int[] map = new int[101];
        if (nums[n - 1] > nums[n - 2]) {
            map[nums[n - 1] - nums[n - 2]]++;
        }
        for (int i = n - 3; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] + nums[j] > 100) {
                    continue;
                }
                res += map[nums[i] + nums[j]];
            }
            for (int k = n - 1; k > i; k--) {
                if (nums[k] < nums[i]) {
                    continue;
                }
                map[nums[k] - nums[i]]++;
            }
        }
        return res;
    }

    // time O(n^3), space O(n)
    public int countQuadrupletsTwoSum(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = n - 1; i >= 3; i--) {
            for (int j = i - 1; j >= 2; j--) {
                if (nums[j] >= nums[i]) {
                    continue;
                }
                res += twoSum(nums, j - 1, nums[i] - nums[j]);
            }
        }
        return res;
    }

    private int twoSum(int[] nums, int end, int target) {
        int res = 0;
        int[] map = new int[101];
        for (int i = 0; i <= end; i++) {
            if (nums[i] >= target) {
                continue;
            }
            res += map[target - nums[i]];
            map[nums[i]]++;
        }
        return res;
    }
}
