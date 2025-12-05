package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/21/2024.
 * #1655 https://leetcode.com/problems/distribute-repeating-integers/
 */
public class DistributeRepeatingIntegers {

    // time O(50^m), space O(m + n)
    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int i = 0;
        int[] count = new int[freq.size()];
        for (int f : freq.values()) {
            count[i++] = f;
        }
        Arrays.sort(quantity);
        return canDistribute(quantity.length - 1, count, quantity);
    }

    private boolean canDistribute(int i, int[] count, int[] quantity) {
        if (i < 0) {
            return true;
        }
        for (int j = 0; j < count.length; j++) {
            if (count[j] >= quantity[i]) {
                count[j] -= quantity[i];
                if (canDistribute(i - 1, count, quantity)) {
                    return true;
                }
                count[j] += quantity[i];
            }
        }
        return false;
    }
}
