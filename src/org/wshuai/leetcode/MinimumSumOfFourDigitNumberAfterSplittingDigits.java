package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/04/2023.
 * #2160 https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/description/
 */
public class MinimumSumOfFourDigitNumberAfterSplittingDigits {

    // time O(1), space O(1)
    public int minimumSum(int num) {
        int[] arr = new int[4];
        int i = 0;
        while (num > 0) {
            arr[i++] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        return arr[0] * 10 + arr[1] * 10 + arr[2] + arr[3];
    }
}
