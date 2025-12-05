package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2025.
 * #2749 https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/
 */
public class MinimumOperationsToMakeTheIntegerZero {

    // time O(log(num1)), space O(1)
    public int makeTheIntegerZero(int num1, int num2) {
        // see https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/editorial/
        for (int k = 1; ; k++) {
            long x = num1 - 1L * num2 * k;
            if (k > x) {
                return -1;
            }
            if (k >= Long.bitCount(x)) {
                return k;
            }
        }
    }
}
