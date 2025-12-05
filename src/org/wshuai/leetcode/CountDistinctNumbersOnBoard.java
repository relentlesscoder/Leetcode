package org.wshuai.leetcode;

/**
 * Created by Wei on 01/24/2024.
 * #2549 https://leetcode.com/problems/count-distinct-numbers-on-board/
 */
public class CountDistinctNumbersOnBoard {

    // time O(1), space O(1)
    public int distinctIntegers(int n) {
        return n == 1 ? 1 : n - 1;
    }
}
