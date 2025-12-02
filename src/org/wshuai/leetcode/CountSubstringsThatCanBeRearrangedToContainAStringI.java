package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #3297 https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i/
 */
public class CountSubstringsThatCanBeRearrangedToContainAStringI {

    // time O(n), space O(1)
    public long validSubstringCount(String word1, String word2) {
        long res = 0;
        int n = word1.length(), unique = 0;
        int[] freq = new int[26];
        for (char c : word2.toCharArray()) {
            if (freq[c - 'a']++ == 0) {
                unique++;
            }
        }
        for (int i = 0, j = 0; i < n; i++) {
            // Note that for characters that are not in word2 this condition will
            // never be satisfied since it will always be negative after the
            // decrement in the frequency array
            if (--freq[word1.charAt(i) - 'a'] == 0) {
                unique--;
            }
            while (unique == 0) {
                // Note that for characters that are not in word2 this condition will
                // never be satisfied since it will always be negative before the
                // increment
                if (freq[word1.charAt(j++) - 'a']++ == 0) {
                    unique++;
                }
            }
            res += j;
        }
        return res;
    }
}
