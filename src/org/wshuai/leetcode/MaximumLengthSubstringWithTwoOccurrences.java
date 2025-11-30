package org.wshuai.leetcode;

/**
 * Created by Wei on 11/30/2025.
 * #3090 https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
 */
public class MaximumLengthSubstringWithTwoOccurrences {

    // time O(n), space O(1)
    public int maximumLengthSubstring(String s) {
        int res = 0, n = s.length();
        int[] freq = new int[26];
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
            while (freq[s.charAt(i) - 'a'] > 2) {
                --freq[s.charAt(j++) - 'a'];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
