package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #1790 https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/description/
 */
public class CheckIfOneStringSwapCanMakeStringsEqual {

    // time O(n), space O(1)
    public boolean areAlmostEqual(String s1, String s2) {
        int diff = 0;
        char c1 = ' ', c2 = ' ';
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff == 0) {
                    c1 = s1.charAt(i);
                    c2 = s2.charAt(i);
                }
                if (diff == 1 && (s1.charAt(i) != c2 || s2.charAt(i) != c1)) {
                    return false;
                }
                diff++;
            }
            if (diff > 2) {
                return false;
            }
        }
        return diff == 0 || diff == 2;
    }
}
