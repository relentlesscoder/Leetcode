package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/29/2025.
 * #3545 https://leetcode.com/problems/minimum-deletions-for-at-most-k-distinct-characters/
 */
public class MinimumDeletionsForAtMostKDistinctCharacters {

    // time O(n), space O(1)
    public int minDeletion(String s, int k) {
        int res = 0, count = 0;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            if (freq[c - 'a']++ == 0) {
                count++;
            }
        }
        Arrays.sort(freq);
        for (int i = 0; i < 26 && count > k; i++) {
            if (freq[i] > 0) {
                count--;
                res += freq[i];
            }
        }
        return res;
    }
}
