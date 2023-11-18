package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2023.
 * #2697 https://leetcode.com/problems/lexicographically-smallest-palindrome/
 */
public class LexicographicallySmallestPalindrome {

    // time O(n), space O(1)
    public String makeSmallestPalindrome(String s) {
        char[] arr = new char[s.length()];
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            char val = ' ', c1 = s.charAt(left), c2 = s.charAt(right);
            val = c1 < c2 ? c1 : c2;
            arr[left++] = val;
            arr[right--] = val;
        }
        return new String(arr);
    }
}
