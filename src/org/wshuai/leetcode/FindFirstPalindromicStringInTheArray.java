package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2023.
 * #2108 https://leetcode.com/problems/find-first-palindromic-string-in-the-array/description/
 */
public class FindFirstPalindromicStringInTheArray {

    // time O(m*n), space O(1)
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindromic(word)) {
                return word;
            }
        }
        return "";
    }

    private boolean isPalindromic(String word) {
        int left = 0, right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left++) != word.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
