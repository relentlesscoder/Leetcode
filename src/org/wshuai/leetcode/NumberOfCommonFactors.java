package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2427 https://leetcode.com/problems/number-of-common-factors/
 */
public class NumberOfCommonFactors {

    // time O(n), space O(1)
    public int commonFactors(int a, int b) {
        int res = 1;
        for (int i = 2; i <= gcd(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                res++;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
