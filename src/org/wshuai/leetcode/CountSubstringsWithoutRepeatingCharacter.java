package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/05/2025.
 * #2743 https://leetcode.com/problems/count-substrings-without-repeating-character/
 */
public class CountSubstringsWithoutRepeatingCharacter {

    // time O(n), space O(1)
    public int numberOfSpecialSubstringsSlidingWindow(String s) {
        // Note that the length of the longest subarray
        // without repeating characters is 26 so the upper
        // bound of total number of valid substring is
        // (26 + 25 + ... + 2 + 1) * (n / 26) <= 351 * 10^5
        int res = 0, n = s.length();
        // No need to store frequency since duplicates is not
        // allowed in any valid sliding window
        boolean[] seen = new boolean[26];
        for (int i = 0, j = 0, duplicates = 0; i < n; i++) {
            while (seen[s.charAt(i) - 'a']) {
                seen[s.charAt(j++) - 'a'] = false;
            }
            seen[s.charAt(i) - 'a'] = true;
            res += i - j + 1;
        }
        return res;
    }

    // time O(n), space O(1)
    public int numberOfSpecialSubstringsBitMask(String s) {
        // Same as solution 1 but use bitmask to save space
        int res = 0, n = s.length(), mask = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            while ((mask & (1 << idx)) > 0) {
                mask -= (1 << (s.charAt(j++) - 'a'));
            }
            mask += (1 << idx);
            res += i - j + 1;
        }
        return res;
    }

    // time O(n), space O(1)
    public int numberOfSpecialSubstringsLastIndex(String s) {
        // More optimized version to skip all indexes before last
        // occurrence of s[i] since they are all invalid
        int res = 0, n = s.length(), mask = 0;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0, j = 0; i < n; i++) {
            if (last[s.charAt(i) - 'a'] != -1) {
                j = Math.max(j, last[s.charAt(i) - 'a'] + 1);
            }
            res += i - j + 1;
            last[s.charAt(i) - 'a'] = i;
        }
        return res;
    }
}
