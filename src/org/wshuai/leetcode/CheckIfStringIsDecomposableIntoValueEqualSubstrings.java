package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #1933 https://leetcode.com/problems/check-if-string-is-decomposable-into-value-equal-substrings/
 */
public class CheckIfStringIsDecomposableIntoValueEqualSubstrings {

    // time O(n), space O(n)
    public boolean isDecomposable(String s) {
        int count = 0;
        char prev = '|';
        boolean seenTwo = false;
        s += "#";
        for (char c : s.toCharArray()) {
            if (c != prev) {
                prev = c;
                if (count % 3 == 2) {
                    if (seenTwo) {
                        return false;
                    } else {
                        seenTwo = true;
                    }
                } else if (count % 3 != 0) {
                    return false;
                }
                count = 0;
            }
            count++;
        }
        return seenTwo;
    }
}
