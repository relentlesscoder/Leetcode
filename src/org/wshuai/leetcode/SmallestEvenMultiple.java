package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2023.
 * #2413 https://leetcode.com/problems/smallest-even-multiple/
 */
public class SmallestEvenMultiple {

    // time O(1), space O(1)
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : (n << 1);
    }
}
