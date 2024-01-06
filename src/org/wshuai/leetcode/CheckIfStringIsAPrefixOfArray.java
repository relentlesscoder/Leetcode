package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #1961 https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/
 */
public class CheckIfStringIsAPrefixOfArray {

    // time O(n), space O(1)
    public boolean isPrefixString(String s, String[] words) {
        int i = 0;
        for (int j = 0; i < s.length() && j < words.length; j++) {
            for (char c : words[j].toCharArray()) {
                if (i == s.length() || c != s.charAt(i++)) {
                    return false;
                }
            }
        }
        return i == s.length();
    }
}
