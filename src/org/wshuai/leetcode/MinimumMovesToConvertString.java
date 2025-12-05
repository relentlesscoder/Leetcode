package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #2027 https://leetcode.com/problems/minimum-moves-to-convert-string/
 */
public class MinimumMovesToConvertString {

    // time O(n), space O(1)
    public int minimumMoves(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                res++;
                i += 2;
            }
        }
        return res;
    }
}
