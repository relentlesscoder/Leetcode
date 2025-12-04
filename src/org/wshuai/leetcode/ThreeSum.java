package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/14/2016.
 * #0015 https://leetcode.com/problems/3sum/
 */
public class ThreeSum {

    // time O(n^2), space O(log(n))
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2 && nums[i] <= 0; i++) {
            // Optimization 1: deduplicate
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // Optimization 2: Since array is sorted, if the sum of 3 least
            // numbers is larger than 0 then it's impossible to find a smaller
            // sum, so we can break early here.
            int s = nums[i] + nums[i + 1] + nums[i + 2];
            if (s > 0) {
                break;
            }
            // Optimization 3: Since array is sorted, if the sum of nums[i] and
            // last two numbers (largest two numbers in array) is smaller than 0
            // then it's impossible to find a greater sum for current nums[i]. But
            // it's still possible to find greater sum using a larger nums[i] so
            // we continue.
            s = nums[i] + nums[n - 2] + nums[n - 1];
            if (s < 0) {
                continue;
            }
            for (int j = i + 1, k = n - 1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                    // Optimization 4 : deduplicate
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}
