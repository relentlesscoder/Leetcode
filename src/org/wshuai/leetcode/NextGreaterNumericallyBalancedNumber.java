package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2025.
 * #2048 https://leetcode.com/problems/next-greater-numerically-balanced-number/
 */
public class NextGreaterNumericallyBalancedNumber {

    // time O(n * log(n)), space O(1)
    public int nextBeautifulNumber(int n) {
        for (int num = n + 1; num <= (int)1e7; num++) {
            if (isBalanced(num)) {
                return num;
            }
        }
        return -1;
    }

    private boolean isBalanced(int num) {
        int[] freq = new int[10];
        while (num > 0) {
            freq[num % 10]++;
            num /= 10;
        }
        if (freq[0] > 0) {
            return false;
        }
        for (int i = 1; i < 10; i++) {
            if (freq[i] > 0 && freq[i] != i) {
                return false;
            }
        }
        return true;
    }
}
