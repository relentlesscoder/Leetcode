package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/08/2024.
 * #2870 https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/
 */
public class MinimumNumberOfOperationsToMakeArrayEmpty {

    // time O(n), space O(n)
    public int minOperations(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            int count = map.get(key), div = count / 3, mod = count % 3;
            if (mod == 0) { // All value count can be removed by group of 3
                res += div;
            } else if (mod == 2) { // One value count needs to be removed by 2
                res += div + 1;
            } else if (div > 0) { // count % 3 == 1, we can borrow 3 values and combine them with the remaining 1 then remove it by group of 2, so we have (div - 1) + (3 + 1) / 2
                res += div + 1;
            } else {
                return -1;
            }
        }
        return res;
    }
}
