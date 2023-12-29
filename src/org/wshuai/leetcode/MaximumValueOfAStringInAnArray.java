package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #2496 https://leetcode.com/problems/maximum-value-of-a-string-in-an-array/
 */
public class MaximumValueOfAStringInAnArray {

    // time O(n), space O(1)
    public int maximumValue(String[] strs) {
        int res = 0;
        for (String str : strs) {
            res = Math.max(res, getValue(str.toCharArray()));
        }
        return res;
    }

    private int getValue(char[] arr) {
        int res = 0, n = arr.length;
        for (char c : arr) {
            if (c < '0' || c > '9') {
                return n;
            }
            res *= 10;
            res += c - '0';
        }
        return res;
    }
}
