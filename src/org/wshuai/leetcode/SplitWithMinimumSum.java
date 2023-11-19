package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/04/2023.
 * #2578 https://leetcode.com/problems/split-with-minimum-sum/
 */
public class SplitWithMinimumSum {

    // time O(n*log(n)), space O(n)
    public int splitNum(int num) {
        int left = 0, right = 0;
        char[] arr = Integer.toString(num).toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] - '0';
            if (left > right) {
                right = right * 10 + digit;
            } else {
                left = left * 10 + digit;
            }
        }
        return left + right;
    }
}
