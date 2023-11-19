package org.wshuai.leetcode;

/**
 * Created by Wei on 03/02/2021.
 * #1680 https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
 */
public class ConcatenationOfConsecutiveBinaryNumbers {

    private static final long MOD = 1_000_000_007;

    // time O(n)
    public int concatenatedBinary(int n) {
        long res = 0;
        for(int i = 1; i <= n; i++){
            String str = Integer.toBinaryString(i);
            res = ((res << str.length()) + i) % MOD;
        }
        return (int)res;
    }
}
