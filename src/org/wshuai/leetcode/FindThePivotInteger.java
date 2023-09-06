package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #2485 https://leetcode.com/problems/find-the-pivot-integer/
 */
public class FindThePivotInteger {

    // time O(n), space O(1)
    public int pivotInteger(int n) {
        int res = -1, prefixSum = 0, sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        for (int i = 1; i <= n; i++) {
            if (prefixSum == sum - prefixSum - i) {
                return i;
            }
            prefixSum += i;
        }
        return -1;
    }
}
