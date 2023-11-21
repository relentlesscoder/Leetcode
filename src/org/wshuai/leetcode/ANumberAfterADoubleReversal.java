package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2119 https://leetcode.com/problems/a-number-after-a-double-reversal/
 */
public class ANumberAfterADoubleReversal {

    // time O(1), space O(1)
    public boolean isSameAfterReversals(int num) {
        return !(num % 10 == 0 && num > 0);
    }
}
