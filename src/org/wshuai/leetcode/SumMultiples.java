package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2023.
 * #2652 https://leetcode.com/problems/sum-multiples/
 */
public class SumMultiples {

    // time O(n), space O(1)
    public int sumOfMultiples(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                res += i;
            }
        }
        return res;
    }
}
