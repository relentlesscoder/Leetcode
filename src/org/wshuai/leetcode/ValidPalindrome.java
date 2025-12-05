package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2020.
 * #0125 https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

    // time O(n), space O(1)
    public boolean isPalindrome(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0, j = n - 1; i < j; ) {
            char left = toLetter(arr[i]);
            char right = toLetter(arr[j]);
            if (left == '#' && right == '#') {
                i++;
                j--;
            } else if (left == '#') {
                i++;
            } else if (right == '#') {
                j--;
            } else if (left == right) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    private char toLetter(char c) {
        if (c >= 'a' && c <= 'z') {
            return c;
        }
        if (c >= 'A' && c <= 'Z') {
            return (char) ('a' + (c - 'A'));
        }
        if (c >= '0' && c <= '9') {
            return c;
        }
        return '#';
    }
}
