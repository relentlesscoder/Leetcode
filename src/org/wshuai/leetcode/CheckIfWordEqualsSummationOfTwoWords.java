package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #1880 https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/
 */
public class CheckIfWordEqualsSummationOfTwoWords {

    // time O(n), space O(1)
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return valueOf(firstWord) + valueOf(secondWord) == valueOf(targetWord);
    }

    private int valueOf(String word) {
        int res = 0;
        for (char c : word.toCharArray()) {
            int d = c - 'a';
            res *= 10;
            res += d;
        }
        return res;
    }
}
