package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/21/2025.
 * #2845 https://leetcode.com/problems/count-of-interesting-subarrays/
 */
public class CountOfInterestingSubarrays {

    // time O(n), space O(min(n, m))
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long res = 0;
        int n = nums.size(), prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i <= n; i++) {
            prefixSum += (nums.get(i - 1) % modulo == k ? 1 : 0);
            int diff = (prefixSum - k + modulo) % modulo;
            res += map.getOrDefault(diff, 0);
            map.put(prefixSum % modulo,
                    map.getOrDefault(prefixSum % modulo, 0) + 1);
        }
        return res;
    }

    // time O(n), space O(n + m)
    public long countInterestingSubarraysPrefixSumArray(List<Integer> nums, int modulo, int k) {
        long res = 0;
        int n = nums.size();
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + (nums.get(i - 1) % modulo == k ? 1 : 0);
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i <= n; i++) {
            int diff = (prefixSum[i] - k + modulo) % modulo;
            res += map.getOrDefault(diff, 0);
            map.put(prefixSum[i] % modulo,
                    map.getOrDefault(prefixSum[i] % modulo, 0) + 1);
        }
        return res;
    }
}
