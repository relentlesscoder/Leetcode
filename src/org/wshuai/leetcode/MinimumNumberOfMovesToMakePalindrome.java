package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2023.
 * #2193 https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/
 */
public class MinimumNumberOfMovesToMakePalindrome {

    // time O(n^2), space O(n)
    public int minMovesToMakePalindrome(String s) {
        if (s.length() <= 2) {
            return 0;
        }
        // greedily find the rightmost character that matches the fist character and swap it with the last character
        int right = s.length() - 1;
        char c = s.charAt(0);
        while (right > 0 && s.charAt(right) != c) {
            right--;
        }
        if (right == 0) { // char c is the only unique character, we need to move it to the center
            return ((s.length() - 1) >> 1) + minMovesToMakePalindrome(s.substring(1));
        } else { // remove the char pair from the string and recursively find the minimum operations for the new string
            return s.length() - 1 - right + minMovesToMakePalindrome(s.substring(1, right) + s.substring(right + 1));
        }
    }
}
