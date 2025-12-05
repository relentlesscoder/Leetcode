package org.wshuai.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 09/30/2019.
 * #1094 https://leetcode.com/problems/car-pooling/
 */
public class CarPooling {

    // time O(n + MAX), space O(MAX)
    public boolean carPooling(int[][] trips, int capacity) {
        int[] counts = new int[1_001];
        for (int[] t : trips) {
            counts[t[1]] += t[0];
            counts[t[2]] -= t[0];
        }
        int current = 0;
        for (int i = 0; i < counts.length; i++) {
            current += counts[i];
            if (current > capacity) {
                return false;
            }
        }
        return true;
    }

    // time O(n * log(n)), space O(n)
    public boolean carPoolingTreeMap(int[][] trips, int capacity) {
        Map<Integer, Integer> counts = new TreeMap<>();
        for (int[] t : trips) {
            counts.merge(t[1], t[0], Integer::sum);
            counts.merge(t[2], -t[0], Integer::sum);
        }
        int current = 0;
        for (int key : counts.keySet()) {
            current += counts.get(key);
            if (current > capacity) {
                return false;
            }
        }
        return true;
    }
}
