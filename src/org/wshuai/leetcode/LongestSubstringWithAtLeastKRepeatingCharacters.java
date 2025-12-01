package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0395 https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    // time O(26 * n), space O(1)
    public int longestSubstring(String s, int k) {
		// Covert the problem into finding the longest substring from:
		// 1. The longest substring with number of N unique characters where
		//    N in [1,26]
		// 2. All characters in above substring repeats no less than k times
        int res = 0;
        for (int i = 1; i <= 26; i++) {
            res = Math.max(res, longestSubstringWithUnique(s, k, i));
        }
        return res;
    }

    private int longestSubstringWithUnique(String s, int k, int m) {
        int res = 0, n = s.length();
        int[] freq = new int[26];
        for (int i = 0, j = 0, distinct = 0, valid = 0; i < n; i++) {
            if (freq[s.charAt(i) - 'a'] == 0) {
                distinct++;
            }
            if (++freq[s.charAt(i) - 'a'] == k) {
                valid++;
            }
            while (distinct > m) {
                if (freq[s.charAt(j) - 'a'] == 1) {
                    distinct--;
                }
                if (freq[s.charAt(j) - 'a']-- == k) {
                    valid--;
                }
                j++;
            }
            if (distinct == m && valid == distinct) {
                res = Math.max(res, i - j + 1);
            }
        }
        return res;
    }
}
