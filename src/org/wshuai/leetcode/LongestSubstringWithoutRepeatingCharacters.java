package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/09/2015.
 * #0003 https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // time O(n), space O(1)
    public int lengthOfLongestSubstring(String s) {
        int res = 0, n = s.length();
        int[] last = new int[128];
        Arrays.fill(last, -1);
        for (int i = 0, j = 0; i < n; i++) {
            // j = max(j, last[c_i] + 1)
            // e.g. abba
            // For index 3, the left of the window should
            // be at current index j 2 instead of index
            // last['a'] + 1 = 1
            j = Math.max(j, last[s.charAt(i)] + 1);
            res = Math.max(res, i - j + 1);
            last[s.charAt(i)] = i;
        }
        return res;
    }

    // time O(n), space O(1)
    public int lengthOfLongestSubstringSlidingWindow(String s) {
        int res = 0, n = s.length();
        int[] freq = new int[128];
        for (int i = 0, j = 0; i < n; i++) {
            freq[s.charAt(i)]++;
            while (freq[s.charAt(i)] > 1) {
                --freq[s.charAt(j++)];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
