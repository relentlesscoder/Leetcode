package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/03/2025.
 * #2780 https://leetcode.com/problems/minimum-index-of-a-valid-split/
 */
public class MinimumIndexOfAValidSplit {

    // time O(n), space O(1)
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size(), votes = 0, count = 0, dominant = -1, freq = 0;
        // Boyer-Moore Majority Voting
        for (int num : nums) {
            if (votes == 0) {
                dominant = num;
                votes = 1;
            } else {
                votes += (num == dominant ? 1 : -1);
            }
        }
        // count frequency of the dominant
        for (int num : nums) {
            if (num == dominant) {
                freq++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums.get(i) == dominant) {
                count++;
            }
            // if:
            //   1. the count of the dominant is more than half in the current subarray [0, i]
            //   2. the rest of the dominant is more than half in the rest of the array [i + 1, n - 1]
            // then this is the minimum index to split
            if (count > (i + 1) / 2 && freq - count > (n - i - 1) / 2) {
                return i;
            }
        }
        return -1;
    }

    // time O(n), space O(n)
    public int minimumIndexHashMap(List<Integer> nums) {
        int n = nums.size(), count = 0, threshold = (n >> 1) + 1, dominant = -1;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            int curr = freq.getOrDefault(num, 0) + 1;
            if (curr == threshold) {
                dominant = num; // check if num is the dominant
            }
            freq.put(num, curr); // save frequency of the current number
        }
        for (int i = 0; i < n; i++) {
            if (nums.get(i) == dominant) {
                count++; // current count of the dominant
            }
            // if:
            //   1. the count of the dominant is more than half in the current subarray [0, i]
            //   2. the rest of the dominant is more than half in the rest of the array [i + 1, n - 1]
            // then this is the minimum index to split
            if (count > (i + 1) / 2 && freq.get(dominant) - count > (n - i - 1) / 2) {
                return i;
            }
        }
        return -1;
    }
}
