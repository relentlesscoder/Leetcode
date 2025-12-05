package org.wshuai.leetcode;

/**
 * Created by Wei on 04/05/2025.
 * #3110 https://leetcode.com/problems/score-of-a-string/
 */
public class ScoreOfAString {

    // time O(n), space O(1)
    public int scoreOfString(String s) {
        int score = 0;
        for (int i = 1; i < s.length(); i++) {
            score += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }
        return score;
    }
}
