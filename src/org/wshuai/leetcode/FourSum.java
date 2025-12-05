package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/25/2016.
 * #0018 https://leetcode.com/problems/4sum/
 */
public class FourSum {

    // time O(n^3), space O(log(n))
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            // Optimization 1: deduplicate
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            long a = (long) nums[i];
            // Optimization 2: Since array is sorted, if the sum of 4 least
            // numbers is larger than 0 then it's impossible to find a smaller
            // sum, so we can break early here.
            if (a + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // Optimization 3: Since array is sorted, if the sum of nums[i] and
            // last three numbers (largest three numbers in array) is smaller than 0
            // then it's impossible to find a greater sum for current nums[i]. But
            // it's still possible to find greater sum using a larger nums[i] so
            // we continue.
            if (a + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                // Deduplicate
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                long b = (long) nums[j];
                if (a + b + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (a + b + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }
                for (int k = j + 1, l = n - 1; k < l; ) {
                    long sum = a + b + nums[k] + nums[l];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k++], nums[l--]));
                        // Optimization 4: deduplicate
                        while (k < l && nums[k] == nums[k - 1]) {
                            k++;
                        }
                        while (k < l && nums[l] == nums[l + 1]) {
                            l--;
                        }
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return res;
    }
}
