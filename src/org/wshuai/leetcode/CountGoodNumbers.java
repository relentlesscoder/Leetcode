package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #1922 https://leetcode.com/problems/count-good-numbers/
 */
public class CountGoodNumbers {

    private static final long MOD = (long)1e9 + 7;

    // time O(log(n)), space O(1)
    public int countGoodNumbers(long n) {
        return (int) ((expo(5, (n + 1) / 2) * expo(4, n / 2)) % MOD);
    }

    // time O(log(n)), space O(log(n))
    private long expo(long x, long y) {
        if (y == 0) {
            return 1;
        }
        if (y % 2 == 1) {
            return (x * expo((x * x) % MOD, (y - 1) / 2)) % MOD;
        } else {
            return expo((x * x) % MOD, (y) / 2);
        }
    }

    // time O(log(n)), space O(log(n))
    private long expoRecursive(long x, long y) {
        if (y == 0) {
            return 1;
        }
        long res = expoRecursive(x, y / 2);
        res = (res * res) % MOD;
        if (y % 2 == 1) {
            res = (res * x) % MOD;
        }
        return res;
    }

    // time O(log(n)), space O(1)
    private long expoIterative(long x, long y) {
        long res = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            y /= 2;
        }
        return res;
    }

/**
 * recursive
 * 11 - x x x x x x x x x x x
 * 5 - (x * x) * (x * x) * x
 * 2 - x * x
 * 1 - 1 * 1 * x
 * 0 - 1

 * iterative
 * 11 - res = 1 * x, x' = x * x, y = 5
 * 5  - res = 1 * x * x * x, x'' = x * x * x * x, y = 2
 * 2  - res = 1 * x * x * x, x''' = x * x * x * x * x * x * x * x, y = 1
 * 1  - res = 1 * x * x * x * x * x * x * x * x * x * x * x, y = 0
 */

}
