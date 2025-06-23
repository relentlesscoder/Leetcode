package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3064 https://leetcode.com/problems/guess-the-number-using-bitwise-questions-i/
 */
public class GuessTheNumberUsingBitwiseQuestionsI {

    // time O(1), space O(1)
    public int findNumber() {
        int res = 0;
        for (int i = 0; i < 30; i++) {
            int curr = (1 << i);
            if (commonSetBits(curr) > 0) {
                res += curr;
            }
        }
        return res;
    }

    /**
     * Definition of commonSetBits API (defined in the parent class Problem).
     * int commonSetBits(int num);
     */
    private int commonSetBits(int num) {
        return 0;
    }
}
