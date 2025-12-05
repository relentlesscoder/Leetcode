package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/08/2025.
 * #2561 https://leetcode.com/problems/rearranging-fruits/
 */
public class RearrangingFruits {

    // time O(n + diff * log(diff)), space O(n)
    public long minCost(int[] basket1, int[] basket2) {
        // https://leetcode.com/problems/rearranging-fruits/editorial/
        long res = 0;
        int min = Integer.MAX_VALUE;;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int cost : basket1) {
            freq.put(cost, freq.getOrDefault(cost, 0) + 1);
            min = Math.min(min, cost);
        }
        for (int cost : basket2) {
            freq.put(cost, freq.getOrDefault(cost, 0) - 1);
            min = Math.min(min, cost);
        }
        List<Integer> merge = new ArrayList<>();
        for (int key : freq.keySet()) {
            int count = Math.abs(freq.get(key));
            if (count % 2 != 0) {
                return -1;
            }
            for (int i = 0; i < count / 2; i++) {
                merge.add(key);
            }
        }
        Collections.sort(merge);
        for (int i = 0; i < merge.size() / 2; i++) {
            res += Math.min((min << 1), merge.get(i));
        }
        return res;
    }
}
