package org.wshuai.leetcode;

/**
 * Created by Wei on 03/13/2017.
 * #0541 https://leetcode.com/problems/reverse-string-ii/
 */
public class ReverseStringII {

    // time O(n), space O(n)
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = s.length();
        for (int x = 0; x < n; x += 2 * k) {
            for (int i = x, j = Math.min(i + k - 1, n - 1); i < j; i++, j--) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return new String(arr);
    }
}
