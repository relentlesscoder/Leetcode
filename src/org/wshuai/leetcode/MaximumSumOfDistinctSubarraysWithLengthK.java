package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/26/2023.
 * #2461 https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
 */
public class MaximumSumOfDistinctSubarraysWithLengthK {

    // time O(n), space O(1)
    public long maximumSubarraySum(int[] nums, int k) {
		long res = 0, sum = 0;
		int n = nums.length, max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		int[] last = new int[max + 1];
		Arrays.fill(last, -1);
		for (int i = 0, j = 0; j < n; j++) {
			// Add constraint j - i + 1 > k to terminate loop
			// when window size is too small
			while (last[nums[j]] != -1 || j - i + 1 > k) {
				sum -= nums[i];
				last[nums[i++]] = -1;
			}
			sum += nums[j];
			last[nums[j]] = j;
			if (j - i + 1 == k) {
				res = Math.max(res, sum);
			}
		}
		return res;
    }

    // time O(n), space O(n)
    public long maximumSubarraySumHashMap(int[] nums, int k) {
        long res = 0, sum = 0;
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            freq.merge(nums[i], 1, Integer::sum);
            if (i - k + 1 < 0) {
                continue;
            }
            if (freq.size() == k) {
                res = Math.max(res, sum);
            }
            sum -= nums[i - k + 1];
            int count = freq.get(nums[i - k + 1]) - 1;
            if (count == 0) {
                freq.remove(nums[i - k + 1]);
            } else {
                freq.put(nums[i - k + 1], count);
            }
        }
        return res;
    }
}
