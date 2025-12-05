package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #2481 https://leetcode.com/problems/minimum-cuts-to-divide-a-circle/
 */
public class MinimumCutsToDivideACircle {

    // time O(1), space O(1)
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        return n % 2 == 0 ? n / 2 : n;
    }
}
