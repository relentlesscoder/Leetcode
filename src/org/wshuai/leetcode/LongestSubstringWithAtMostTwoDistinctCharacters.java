package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    // time O(n), space O(1)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int res = 0, n = s.length();
        int[] freq = new int[128]; // Use hash map if the upper bound is not provided
        for (int i = 0, j = 0, distinct = 0; i < n; i++) {
            if (freq[s.charAt(i)]++ == 0) {
                distinct++;
            }
            while (distinct > 2) {
                if (--freq[s.charAt(j++)] == 0) {
                    distinct--;
                }
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
