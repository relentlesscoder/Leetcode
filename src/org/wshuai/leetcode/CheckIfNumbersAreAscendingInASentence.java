package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2042 https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence/
 */
public class CheckIfNumbersAreAscendingInASentence {

    // time O(n), space O(1)
    public boolean areNumbersAscending(String s) {
        s += " ";
        int num = 0, prev = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num *= 10;
                num += (c - '0');
            } else if (c == ' ') {
                if (num > 0) {
                    if (num <= prev) {
                        return false;
                    }
                    prev = num;
                }
                num = 0;
            }
        }
        return true;
    }
}
