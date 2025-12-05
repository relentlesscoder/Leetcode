package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #2716 https://leetcode.com/problems/minimize-string-length/
 */
public class MinimizeStringLength {

    // time O(n), space O(1)
    public int minimizedStringLength(String s) {
        int res = 0;
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 'a']++ == 0) {
                res++;
            }
        }
        return res;
    }
}
