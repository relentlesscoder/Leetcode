package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2375 https://leetcode.com/problems/construct-smallest-number-from-di-string/
 */
public class ConstructSmallestNumberFromDIString {

    // time O(n), space O(n)
    public String smallestNumber(String pattern) {
        StringBuilder res = new StringBuilder(), rev = new StringBuilder();
        for (int i = 0; i <= pattern.length(); i++) {
            rev.append((char)('1' + i));
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                res.append(rev.reverse());
                rev = new StringBuilder();
            }
        }
        return res.toString();
    }

    // time O(n), space O(n)
    public String smallestNumberReverseSubstring(String pattern) {
        int n = pattern.length() + 1;
        char[] res = new char[n];
        res[0] = '1';
        for (int i = 1; i < n; i++) {
            res[i] = (char)(res[i - 1] + 1);
        }
        for (int i = pattern.length() - 1; i >= 0; i--) { // initialize the string to ['1', '2', '3', '4' ... 'n'] and reverse all substrings like 'DDD'
            if (pattern.charAt(i) == 'D') {
                int j = i;
                while (j >= 0 && pattern.charAt(j) == 'D') {
                    j--;
                }
                reverse(res, j + 1, i + 1);
                i = j;
            }
        }
        return new String(res);
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
}
