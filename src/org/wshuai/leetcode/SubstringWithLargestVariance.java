package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2023.
 * #2272 https://leetcode.com/problems/substring-with-largest-variance/
 */
public class SubstringWithLargestVariance {

    // time O(n), space O(1)
    public int largestVariance(String s) {
        int res = 0;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        // for each character i and j, calculate the subarray that has max variance of freq(i) - freq(j)
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j || count[i] == 0 || count[j] == 0) {
                    continue;
                }
                char major = (char)('a' + i), minor = (char)('a' + j);
                int majorCount = 0, minorCount = 0, resetMinor = count[j];
                for (char c : s.toCharArray()) {
                    if (c == major) {
                        majorCount++;
                    } else if (c == minor) {
                        minorCount++;
                        resetMinor--;
                    }
                    if (minorCount > 0) { // valid substring contains at least one minor
                        res = Math.max(res, majorCount - minorCount);
                    }
                    if (majorCount < minorCount && resetMinor > 0) { // ensure the remaining has at least one minor
                        majorCount = 0;
                        minorCount = 0;
                    }
                }
            }
        }
        return res;
    }
}
