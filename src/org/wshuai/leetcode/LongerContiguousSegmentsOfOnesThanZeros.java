package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #1869 https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/
 */
public class LongerContiguousSegmentsOfOnesThanZeros {

    // time O(n), space O(1)
    public boolean checkZeroOnes(String s) {
        int lzeros = 0, lones = 0, zeros = 0, ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                zeros = 0;
                lones = Math.max(lones, ++ones);
            } else {
                ones = 0;
                lzeros = Math.max(lzeros, ++zeros);
            }
        }
        return lones > lzeros;
    }
}
