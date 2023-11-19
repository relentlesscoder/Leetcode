package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/18/2023.
 * #2404 https://leetcode.com/problems/most-frequent-even-element/
 */
public class MostFrequentEvenElement {

    // time O(n), space O(n)
    public int mostFrequentEven(int[] nums) {
        int maxCount = -1, res = 100_001;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 1) {
                continue;
            }
            int count = counter.getOrDefault(num, 0) + 1;
            counter.put(num, count);
            if (count > maxCount || (count == maxCount && num < res)) {
                maxCount = count;
                res = num;
            }
        }
        return res == 100_001 ? -1 : res;
    }
}
