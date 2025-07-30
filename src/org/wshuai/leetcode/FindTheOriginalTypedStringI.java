package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2025.
 * #3330 https://leetcode.com/problems/find-the-original-typed-string-i/
 */
public class FindTheOriginalTypedStringI {

    // time O(n), space O(1)
    public int possibleStringCount(String word) {
        int res = 1, n = word.length();
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                res++;
            }
        }
        return res;
    }
}
