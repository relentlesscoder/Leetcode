package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/25/2019.
 * #0611 https://leetcode.com/problems/valid-triangle-number/
 */
public class ValidTriangleNumber {

    // time O(n^2), space O(1)
    public int triangleNumber(int[] nums) {
        // Sort the array then for each nums[k] in [2, n - 1], find how many pairs
        // [i, j] from its left satisfy nums[i] + nums[j] > nums[k]
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int k = 2; k < n; k++) {
            int i = 0, j = k - 1;
            // Optimization 1: If the sum of least two numbers are larger than
            //   nums[k] then all pairs in [i, j] can form a triangle with k.
            //   The total number is (j - i) * (j - i + 1) / 2.
            if (nums[i] + nums[i + 1] > nums[k]) {
                res += (j - i) * (j - i + 1) / 2;
                continue;
            }
            // Optimization 2: If the sum of largest two numbers are smaller than
            //   nums[k] then all pairs in [i, j] are invalid.
            if (nums[k - 2] + nums[k - 1] < nums[k]) {
                continue;
            }
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum > nums[k]) {
                    res += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return res;
    }

    // time O(n^2), space O(1)
    public int triangleNumberShortestSide(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int k = 0; k < n - 2; k++) {
            if (nums[k] == 0) {
                continue;
            }
            for (int i = k + 1, j = k + 2; j < n; j++) {
                while (nums[j] - nums[i] >= nums[k]) {
                    i++;
                }
                res += j - i;
            }
        }
        return res;
    }
}
