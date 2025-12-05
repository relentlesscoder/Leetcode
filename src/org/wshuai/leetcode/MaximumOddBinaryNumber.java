package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #2864 https://leetcode.com/problems/maximum-odd-binary-number/
 */
public class MaximumOddBinaryNumber {

    // time O(n), space O(1)
    public String maximumOddBinaryNumber(String s) {
        char[] arr = s.toCharArray();
        int n = s.length(), i = 0, j = 0;
        for (; i < n; i++) {
            if (arr[i] == '1') {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j++] = temp;
            }
        }
        char temp = arr[j - 1];
        arr[j - 1] = arr[n - 1];
        arr[n - 1] = temp;
        return new String(arr);
    }
}
