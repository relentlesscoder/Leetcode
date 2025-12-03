package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2016.
 * #0344 https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {

    // time O(n), space O(1)
    public void reverseString(char[] s) {
        int n = s.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
