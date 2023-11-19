package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2023.
 * #2894 https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/
 */
public class DivisibleAndNonDivisibleSumsDifference {

    // time O(n), space O(1)
    public int differenceOfSums(int n, int m) {
        int num1 = 0, num2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                num2 += i;
            } else {
                num1 += i;
            }
        }
        return num1 - num2;
    }
}
