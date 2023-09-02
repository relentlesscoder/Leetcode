package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/03/2023.
 * #1925 https://leetcode.com/problems/count-square-sum-triples/description/
 */
public class CountSquareSumTriples {

    // time O(n^2), space O(n)
    public int countTriples(int n) {
        int res = 0, maxSquare = n*n;
        Set<Integer> targetSum = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            targetSum.add(i*i);
        }
        for (int i = 1; i <= n; i++) {
            int currSquare = i*i;
            for (int j = i + 1; currSquare + j*j <= maxSquare; j++) {
                if (targetSum.contains(currSquare + j*j)) {
                    res += 2;
                }
            }
        }
        return res;
    }
}
