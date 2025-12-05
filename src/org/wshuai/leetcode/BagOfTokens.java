package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2019.
 * #0948 https://leetcode.com/problems/bag-of-tokens/
 */
public class BagOfTokens {

    // time O(n * log(n)), space O(log(n))
    public int bagOfTokensScore(int[] tokens, int power) {
        if (tokens.length == 0 || power == 0) {
            return 0;
        }
        // Since we only need 1 score to get a token with any power,
        // and we need token[i] power to exchange 1 score with it.
        // The optimal strategy is we exchange score with token with
        // least power and then use 1 score to exchange max power from
        // token with the largest power and repeat until we run out of
        // power.
        int n = tokens.length, score = 0;
        // Sort the tokens
        Arrays.sort(tokens);
        if (tokens[0] > power) {
            return 0;
        }
        for (int i = 0, j = n - 1; i <= j; ) {
            // Exchange as much score as we can if we have enough power
            if (power >= tokens[i]) {
                power -= tokens[i++];
                score++;
            } else if (score > 0 && i != j) {
                // If we don't have enough power, use 1 score to exchange the
                // token with max amount of power. Note that if we don't have
                // any token left to gain score (i == j) we don't need to spend
                // 1 more score.
                power += tokens[j--];
                score--;
            } else {
                break;
            }
        }
        return score;
    }
}
