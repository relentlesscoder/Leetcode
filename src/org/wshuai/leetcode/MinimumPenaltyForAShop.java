package org.wshuai.leetcode;

/**
 * Created by Wei on 09/11/2023.
 * #2149 https://leetcode.com/problems/rearrange-array-elements-by-sign/description/
 */
public class MinimumPenaltyForAShop {

    // time O(n), space O(1)
    public int bestClosingTime(String customers) {
        int penalty = 0, minPenalty = 0, res = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                penalty--;
            } else {
                penalty++;
            }
            if (penalty < minPenalty) {
                minPenalty = penalty;
                res = i + 1;
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public int bestClosingTimeTwoPasses(String customers) {
        int penalty = 0, minPenalty = 0, res = 0;
        for (int i = 0; i < customers.length(); i++) {
            penalty += (customers.charAt(i) == 'Y') ? 1 : 0;
        }
        minPenalty = penalty;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                penalty--;
            } else {
                penalty++;
            }
            if (penalty < minPenalty) {
                minPenalty = penalty;
                res = i + 1;
            }
        }
        return res;
    }
}
