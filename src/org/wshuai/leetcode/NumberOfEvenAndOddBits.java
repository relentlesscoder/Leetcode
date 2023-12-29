package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #2595 https://leetcode.com/problems/number-of-even-and-odd-bits/
 */
public class NumberOfEvenAndOddBits {

    // time O(log(n)), space O(1)
    public int[] evenOddBit(int n) {
        int a = 0, b = 0, flag = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                a = flag == 1 ? a + 1 : a;
                b = flag == 0 ? b + 1 : b;
            }
            n /= 2;
            flag = 1 - flag;
        }
        return new int[] {a, b};
    }
}
