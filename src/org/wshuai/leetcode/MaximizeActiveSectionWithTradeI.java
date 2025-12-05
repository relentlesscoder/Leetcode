package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2025.
 * #3499 https://leetcode.com/problems/maximize-active-section-with-trade-i/
 */
public class MaximizeActiveSectionWithTradeI {

    // time O(n), space O(1)
    public int maxActiveSectionsAfterTrade(String s) {
        // Find substring like 001110000 with max count of zeros,
        // the result is this max count of zeros + all ones
        int n = s.length();
        int pre = 0; // Number of zeros in previous all zero segment
        int ones = 0; // Total count of all ones in s
        int zeros = 0; // Maximum number of zeros in substring like 001110000
        for (int i = 0, curr = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                curr++;
                if (s.charAt(i) == '0' && (i == n - 1 || s.charAt(i + 1) == '1')) {
                    if (pre > 0) {
                        zeros = Math.max(zeros, pre + curr);
                    }
                    pre = curr;
                    curr = 0;
                }
            }
        }
        return ones + zeros;
    }
}
