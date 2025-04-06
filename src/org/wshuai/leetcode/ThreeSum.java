package org.wshuai.leetcode;

import java.util.*;

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
        for (int i = 0; i < n - 2 && nums[i] <= 0; i++) { // early termination when nums[i] > 0
            // deduplicate
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i], j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                    // deduplicate
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}
