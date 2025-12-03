package org.wshuai.leetcode;

/**
 * Created by Wei on 01/08/2024.
 * #1750 https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/
 */
public class MinimumLengthOfStringAfterDeletingSimilarEnds {

    // time O(n), space O(1)
    public int minimumLength(String s) {
        int n = s.length(), i = 0, j = n - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            char c = s.charAt(i);
            // i <= j to handle two corner cases:
            //   1. [b, b, a, a, a, b] where i = j + 1 at the end and
            //      the result should be 0.
            //   2. [a, a, b, a, a, a] where i == j at the end and the
            //      result should be 1.
            while (i <= j && s.charAt(i) == c) {
                i++;
            }
            while (i < j && s.charAt(j) == c) {
                j--;
            }
        }
        return j - i + 1;
    }
}
