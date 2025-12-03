package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2019.
 * #0917 https://leetcode.com/problems/reverse-only-letters/
 */
public class ReverseOnlyLetters {

    // time O(n), space O(n)
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0, j = n - 1; i < j; ) {
            boolean left = isLetter(arr[i]);
            boolean right = isLetter(arr[j]);
            if (left && right) {
                char temp = arr[i];
                arr[i++] = arr[j];
                arr[j--] = temp;
            } else if (left == right) {
                i++;
                j--;
            } else if (left) {
                j--;
            } else {
                i++;
            }
        }
        return new String(arr);
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
