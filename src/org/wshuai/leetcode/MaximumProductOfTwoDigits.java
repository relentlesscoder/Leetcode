package org.wshuai.leetcode;

/**
 * Created by Wei on 08/06/2025.
 * #3536 https://leetcode.com/problems/maximum-product-of-two-digits/
 */
public class MaximumProductOfTwoDigits {

    // time O(d), space O(1)
    public int maxProduct(int n) {
        int[] freq = new int[10];
        while (n > 0) {
            freq[n % 10]++;
            n /= 10;
        }
        int d1 = -1;
        for (int i = 9; i >= 0; i--) {
            while (freq[i]-- > 0) {
                if (d1 == -1) {
                    d1 = i;
                } else {
                    return d1 * i;
                }
            }
        }
        return -1;
    }
}
