package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/26/2023.
 * #1658 https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 */
public class MinimumOperationsToReduceXToZero {

    // time O(n), space O(1)
    public int minOperationsSlidingWindow(int[] nums, int x) {
        // The problem equals to find the longest subarray with sum
        // equals to sum - x
        int sum = 0, n = nums.length, longest = -1;
        for (int num : nums) {
            sum += num;
        }
        sum -= x;
        for (int i = 0, j = 0, curr = 0; i < n; i++) {
            curr += nums[i];
            while (j < n && curr > sum) {
                curr -= nums[j++];
            }
            if (curr == sum) {
                longest = Math.max(longest, i - j + 1);
            }
        }
        return longest == -1 ? -1 : n - longest;
    }

    // time O(n), space O(n)
    public int minOperationsPrefixSum(int[] nums, int x) {
        int sum = 0, n = nums.length, longest = -1;
        for (int num : nums) {
            sum += num;
        }
        sum -= x;
        if (sum == 0) {
            return n;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, curr = 0; i < n; i++) {
            curr += nums[i];
            if (map.containsKey(curr - sum)) {
                longest = Math.max(longest, i - map.get(curr - sum));
            }
            map.putIfAbsent(curr, i);
        }
        return longest == -1 ? -1 : n - longest;
    }
}
