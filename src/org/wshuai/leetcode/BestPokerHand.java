package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2023.
 * #2347 https://leetcode.com/problems/best-poker-hand/description/
 */
public class BestPokerHand {

    // time O(n), space O(1)
    public String bestHand(int[] ranks, char[] suits) {
        int[] rankCount = new int[13], suitCount = new int[4];
        boolean piarFound = false;
        for (char s : suits) {
            if (++suitCount[s - 'a'] == 5) {
                return "Flush";
            }
        }
        for (int r : ranks) {
            rankCount[r - 1]++;
            if (rankCount[r - 1] == 3) {
                return "Three of a Kind";
            } else if (rankCount[r - 1] == 2) {
                piarFound = true;
            }
        }
        return piarFound ? "Pair" : "High Card";
    }
}
