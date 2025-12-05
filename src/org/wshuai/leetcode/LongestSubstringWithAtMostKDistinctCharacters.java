package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0340 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    // time O(n), space O(1)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 0, n = s.length();
        int[] freq = new int[128]; // Use hash map if the upper bound is not provided
        for (int i = 0, j = 0, distinct = 0; i < n; i++) {
            if (freq[s.charAt(i)]++ == 0) {
                distinct++;
            }
            while (distinct > k) {
                if (--freq[s.charAt(j++)] == 0) {
                    distinct--;
                }
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
