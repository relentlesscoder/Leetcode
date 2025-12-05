package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #1941 https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
 */
public class CheckIfAllCharactersHaveEqualNumberOfOccurrences {

    // time O(n), space O(1)
    public boolean areOccurrencesEqual(String s) {
        int max = 0;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            max = Math.max(max, ++freq[c - 'a']);
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0 && freq[i] != max) {
                return false;
            }
        }
        return true;
    }
}
