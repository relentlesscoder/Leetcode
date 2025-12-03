package org.wshuai.leetcode;

/**
 * Created by Wei on 07/17/2017.
 * #0557 https://leetcode.com/problems/reverse-words-in-a-string-iii/
 */
public class ReverseWordsInAStringIII {

    // time O(n), space O(n)
    public String reverseWords(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0, j = 0; i <= n; i++) {
            if (i == n || s.charAt(i) == ' ') {
                reverse(arr, j, i - 1);
                j = i + 1;
            }
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int s, int e) {
        for (int i = s, j = e; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
