package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0345 https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
public class ReverseVowelsOfAString {

    // time O(n), space O(1)
    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0, j = n - 1; i < j; ) {
            boolean left = isVowel(arr[i]);
            boolean right = isVowel(arr[j]);
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

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
