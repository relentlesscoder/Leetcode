package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/06/2024.
 * #1788 https://leetcode.com/problems/maximize-the-beauty-of-the-garden/
 */
public class MaximizeTheBeautyOfTheGarden {

    // time O(n), space O(n)
    public int maximumBeauty(int[] flowers) {
        int res = Integer.MIN_VALUE, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int flower : flowers) {
            if (map.containsKey(flower)) {
                res = Math.max(res, (flower << 1) + sum - map.get(flower));
            }
            sum += Math.max(flower, 0);
            map.putIfAbsent(flower, sum);
        }
        return res;
    }

    // time O(n), space O(n)
    public int maximumBeautyVerbose(int[] flowers) {
        int res = Integer.MIN_VALUE, n = flowers.length;
        int[] prefixSum = new int[n + 1];
        Map<Integer, int[]> map = new HashMap<>(); // For each element, finds the rightmost equal element to form the valid garden since the last equal element is always optimal
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + Math.max(flowers[i], 0); // Maintain a prefix sum excluding all negative values
            if (map.containsKey(flowers[i])) {
                map.get(flowers[i])[1] = i;
            } else {
                map.put(flowers[i], new int[]{i, -1});
            }
        }
        for (int key : map.keySet()) {
            int left = map.get(key)[0], right = map.get(key)[1];
            if (right == -1) {
                continue;
            }
            res = Math.max(res, (key << 1) + prefixSum[right] - prefixSum[left + 1]); // The beauty of the garden is the sum of two boundaries plus the sum of all non-negative elements
        }
        return res;
    }
}
