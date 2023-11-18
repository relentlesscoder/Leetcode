package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2023.
 * #2706 https://leetcode.com/problems/buy-two-chocolates/
 */
public class BuyTwoChocolates {

    // time O(n), space O(1)
    public int buyChoco(int[] prices, int money) {
        int min = 101, secondMin = 101, leftover = 0;
        for (int p : prices) {
            if (p < min) {
                secondMin = min;
                min = p;
            } else if (p == min) {
                secondMin = min;
            } else if (p < secondMin) {
                secondMin = p;
            }
        }
        leftover = money - min - secondMin;
        return leftover < 0 ? money : leftover;
    }
}
