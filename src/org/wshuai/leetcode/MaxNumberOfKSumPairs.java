package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/24/2020.
 * #1679 https://leetcode.com/problems/max-number-of-k-sum-pairs/
 */
public class MaxNumberOfKSumPairs {

    // time O(n * log(n)), space O(1)
    public int maxOperations(int[] nums, int k) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0, j = n - 1; i < j; ) {
            int left = nums[i], right = nums[j], sum = nums[i] + nums[j];
            if (sum == k) {
                res++;
                i++;
                j--;
            } else if (sum > k) {
                while (j > i && nums[j] == right) {
                    j--;
                }
            } else {
                while (j > i && nums[i] == left) {
                    i++;
                }
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int maxOperationsHashMap(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int count = map.getOrDefault(k - nums[i], 0);
            if (count > 0) {
                map.put(k - nums[i], --count);
                res++;
            } else {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        return res;
    }
}
