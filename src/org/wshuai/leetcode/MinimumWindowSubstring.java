package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0076 https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

    // time O(m + n), space O(1)
    public String minWindow(String s, String t) {
        int m = s.length(), index = -1, len = m + 1, unmatched = 0;
        int[] freq = new int[128];
        boolean[] seen = new boolean[128];
        for (char c : t.toCharArray()) {
            if (freq[c]++ == 0) {
                unmatched++;
            }
            seen[c] = true;
        }
        for (int i = 0, j = 0; i < m; i++) {
            if (seen[s.charAt(i)] && --freq[s.charAt(i)] == 0) {
                unmatched--;
            }
            while (unmatched == 0) {
                if (i - j + 1 < len) {
                    len = Math.min(len, i - j + 1);
                    index = j;
                }
                if (seen[s.charAt(j)] && freq[s.charAt(j)]++ == 0) {
                    unmatched++;
                }
                j++;
            }
        }
        return index == -1 ? "" : s.substring(index, index + len);
    }
}
