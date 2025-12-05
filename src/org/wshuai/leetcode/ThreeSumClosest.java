package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/21/2016.
 * #0016 https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {

    // time O(n^2), space O(log(n))
    public int threeSumClosest(int[] nums, int target) {
        int res = 0, n = nums.length, diff = (int) 1e6;
        Arrays.sort(nums);
        for (int k = 0; k < n - 2; k++) {
            // Optimization 1: deduplicate
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            // Optimization 2: since array is sorted, if the sum of 3 least
            // numbers is larger than 0 then it's impossible to find a smaller
            // sum, so we can break early here.
            int s = nums[k] + nums[k + 1] + nums[k + 2];
            if (s > target) {
                if (s - target < diff) {
                    res = s;
                }
                break;
            }
            // Optimization 3: since array is sorted, if the sum of nums[i] and
            // last two numbers (largest two numbers in array) is smaller than 0
            // then it's impossible to find a greater sum for current nums[i]. But
            // it's still possible to find greater sum using a larger nums[i] so
            // we continue.
            s = nums[k] + nums[n - 2] + nums[n - 1];
            if (s < target) {
                if (target - s < diff) {
                    diff = target - s;
                    res = s;
                }
                continue;
            }
            for (int i = k + 1, j = n - 1; i < j; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                int d = 0;
                if (sum > target) {
                    d = sum - target;
                    j--;
                } else if (sum < target) {
                    d = target - sum;
                    i++;
                }
                if (d < diff) {
                    diff = d;
                    res = sum;
                }
            }
        }
        return res;
    }
}
