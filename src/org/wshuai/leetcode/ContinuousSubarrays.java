package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/12/2023.
 * #2762 https://leetcode.com/problems/continuous-subarrays/
 */
public class ContinuousSubarrays {

    // time O(n), space O(n)
    public long continuousSubarrays(int[] nums) {
        long res = 0;
        Map<Integer, Integer> lastIndex = new HashMap<>();
        // for each subarray {i, j}, it adds j - i + 1 continuous subarrays to the result
        // for example, 2434 -> 4, 34, 434, 2434
        for (int i = 0, j = 0; j < nums.length; j++) {
            List<Integer> keysToRemove = new ArrayList<>();
            // due to the attribute of continuous subarray, this map contains limited elements
            // so the time complexity for looping it can be treated as constant
            for (Map.Entry<Integer, Integer> entry : lastIndex.entrySet()) {
                int key = entry.getKey();
                if (Math.abs(key - nums[j]) > 2) {
                    i = Math.max(i, entry.getValue() + 1);
                    keysToRemove.add(key);
                }
            }
            for (int k : keysToRemove) {
                lastIndex.remove(k);
            }
            lastIndex.put(nums[j], j);
            res += j - i + 1;
        }
        return res;
    }
}
