package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3121 https://leetcode.com/problems/count-the-number-of-special-characters-ii/
 */
public class CountTheNumberOfSpecialCharactersII {

    // time O(n), space O(1)
    public int numberOfSpecialChars(String word) {
        int res = 0, n = word.length();
        int[] map = new int[52];
        for (int i = 0; i < 52; i++) {
            if (i < 26) {
                map[i] = -1;
            } else {
                map[i] = n;
            }
        }
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                map[c - 'a'] = Math.max(map[c - 'a'], i);
            } else {
                map[c - 'A' + 26] = Math.min(map[c - 'A' + 26], i);
            }
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] >= 0 && map[i + 26] < n && map[i + 26] > map[i]) {
                res++;
            }
        }
        return res;
    }
}
