package org.wshuai.leetcode;

/**
 * Created by Wei on 04/05/2025.
 * #2914 https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
 */
public class MinimumNumberOfChangesToMakeBinaryStringBeautiful {

    // time O(n), space O(1)
    public int minChanges(String s) {
        int res = 0, n = s.length();
        for (int i = 0; i < n; i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                res++;
            }
        }
        return res;
    }
}
