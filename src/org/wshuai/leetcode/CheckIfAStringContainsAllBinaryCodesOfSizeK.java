package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 05/31/2020.
 * #1461 https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {

    // time O(n), space O(2^k)
    public boolean hasAllCodesOptimized(String s, int k) {
        int n = s.length(), upper = (1 << k), val = 0, mask = upper - 1, count = upper;
        int[] map = new int[upper];
        for (int i = 0; i < n; i++) {
            val = (mask & (val << 1)) + s.charAt(i) - '0';
            if (i - k + 1 < 0) {
                continue;
            }
            if (val >= 0 && val <= upper) {
                if (map[val]++ == 0) {
                    count--;
                }
            }
        }
        return count == 0;
    }

    // time O(n), space O(2^k)
    public boolean hasAllCodesHashSet(String s, int k) {
        int n = s.length(), upper = (1 << k), val = 0, mask = upper - 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            val = (mask & (val << 1)) + s.charAt(i) - '0';
            if (i - k + 1 < 0) {
                continue;
            }
            if (val >= 0 && val <= upper) {
                set.add(val);
            }
        }
        return set.size() == upper;
    }
}
