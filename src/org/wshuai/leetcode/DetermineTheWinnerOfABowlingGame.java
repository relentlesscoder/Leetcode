package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2023.
 * #2660 https://leetcode.com/problems/determine-the-winner-of-a-bowling-game/submissions/
 */
public class DetermineTheWinnerOfABowlingGame {

    // time O(m+n), space O(1)
    public int isWinner(int[] player1, int[] player2) {
        int s1 = getScore(player1), s2 = getScore(player2);
        if (s1 > s2) {
            return 1;
        } else if (s2 > s1) {
            return 2;
        }
        return 0;
    }

    private int getScore(int[] player) {
        int score = player[0];
        for (int i = 1; i < player.length; i++) {
            score += player[i];
            if (player[i - 1] == 10 || (i - 2 >= 0 && player[i - 2] == 10)) {
                score += player[i];
            }
        }
        return score;
    }
}
