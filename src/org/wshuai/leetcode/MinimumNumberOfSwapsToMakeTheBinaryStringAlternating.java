package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2023.
 * #1864 https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/
 */
public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {

    // time O(n), space O(1)
    public int minSwaps(String s) {
        int res = 0, zeros = 0, ones = 0, counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            }
        }
        ones = s.length() - zeros;
        if (Math.abs(ones - zeros) > 1) {
            return -1;
        }
        if (ones > zeros) { // "10101"
            return countSwaps(s, '1');
        } else if (zeros > ones) { // "01010"
            return countSwaps(s, '0');
        }
        // "101010" or "010101"
        return Math.min(countSwaps(s, '1'), countSwaps(s, '0'));
    }

    private int countSwaps(String s, char expected) {
        int wrongPositions = 0;
        for (char curr : s.toCharArray()) {
            if (curr != expected) {
                wrongPositions++;
            }
            expected = expected == '1' ? '0' : '1';
        }
        return wrongPositions / 2;
    }
}
