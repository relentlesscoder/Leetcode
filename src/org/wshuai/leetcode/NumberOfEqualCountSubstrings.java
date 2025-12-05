package org.wshuai.leetcode;

/**
 * Created by Wei on 11/29/2025.
 * #2067 https://leetcode.com/problems/number-of-equal-count-substrings/
 */
public class NumberOfEqualCountSubstrings {

    // time O(26 * n), space O(1)
    public int equalCountSubstrings(String s, int count) {
        // Same as #2953
        int res = 0, n = s.length();
        for (int i = 1; i <= 26 && i * count <= n; i++) {
            int k = i * count, distinct = 0, overloaded = 0;
            int[] freq = new int[26];
            for (int j = 0; j < n; j++) {
                if (freq[s.charAt(j) - 'a'] == 0) {
                    distinct++;
                }
                if (freq[s.charAt(j) - 'a']++ == count) {
                    overloaded++;
                }
                int left = j - k + 1;
                if (left < 0) {
                    continue;
                }
                if (distinct == i && overloaded == 0) {
                    res++;
                }
                if (freq[s.charAt(left) - 'a'] == 1) {
                    distinct--;
                }
                if (--freq[s.charAt(left) - 'a'] == count) {
                    overloaded--;
                }
            }
        }
        return res;
    }
}
