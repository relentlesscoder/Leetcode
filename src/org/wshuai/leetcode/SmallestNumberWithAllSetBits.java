package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3370 https://leetcode.com/problems/smallest-number-with-all-set-bits/
 */
public class SmallestNumberWithAllSetBits {

    // time O(log(n)), space O(1)
    public int smallestNumber(int n) {
        int max = 0;
        for (int i = 0; i < 11; i++) {
            if (((1 << i) & n) > 0) {
                max = i;
            }
        }
        return (1 << (max + 1)) - 1;
    }
}
