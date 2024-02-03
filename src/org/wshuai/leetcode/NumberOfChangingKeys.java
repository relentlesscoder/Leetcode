package org.wshuai.leetcode;

/**
 * Created by Wei on 02/03/2024.
 * #3019 https://leetcode.com/problems/number-of-changing-keys/
 */
public class NumberOfChangingKeys {

    // time O(n), space O(1)
    public int countKeyChanges(String s) {
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            char c1 = s.charAt(i - 1), c2 = s.charAt(i);
            if (c1 != c2 && Math.abs(c1 - c2) != 32) {
                res++;
            }
        }
        return res;
    }
}
