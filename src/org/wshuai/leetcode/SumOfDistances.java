package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/11/2025.
 * #2615 https://leetcode.com/problems/sum-of-distances/
 */
public class SumOfDistances {

    // time O(n), space O(n)
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        Map<Integer, long[]> prefixSumMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.putIfAbsent(nums[i], new ArrayList<>());
            indexMap.get(nums[i]).add(i);
        }
        for (int key : indexMap.keySet()) {
            List<Integer> indexes = indexMap.get(key);
            if (indexes.size() == 1) {
                continue;
            }
            long[] prefixSum = new long[indexes.size() + 1];
            for (int i = 1; i <= indexes.size(); i++) {
                prefixSum[i] = prefixSum[i - 1] + indexes.get(i - 1);
            }
            prefixSumMap.put(key, prefixSum);
        }
        for (int i = 0; i < n; i++) {
            if (!prefixSumMap.containsKey(nums[i])) {
                res[i] = 0;
            } else {
                int index = countMap.getOrDefault(nums[i], 0);
                long[] prefixSum = prefixSumMap.get(nums[i]);
                // calculate left side: res[i] += 1L * index * i - prefixSum[index];
                // calculate right side: res[i] += prefixSum[prefixSum.length - 1] - prefixSum[index + 1] - 1L * (prefixSum.length - 1 - (index + 1)) * i;
                res[i] = (2L * index - prefixSum.length + 2) * i
                        - prefixSum[index] + prefixSum[prefixSum.length - 1] - prefixSum[index + 1];
                countMap.put(nums[i], index + 1);
            }
        }
        return res;
    }
}
