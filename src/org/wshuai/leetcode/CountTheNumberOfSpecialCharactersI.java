package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3120 https://leetcode.com/problems/count-the-number-of-special-characters-i/
 */
public class CountTheNumberOfSpecialCharactersI {

    // time O(n), space O(1)
    public int numberOfSpecialChars(String word) {
        int res = 0;
        int[] map = new int[52];
        for (char c : word.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                map[c - 'a']++;
            } else {
                map[c - 'A' + 26]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0 && map[i + 26] > 0) {
                res++;
            }
        }
        return res;
    }
}
