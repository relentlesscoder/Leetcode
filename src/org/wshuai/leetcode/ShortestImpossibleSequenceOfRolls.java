package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/16/2023.
 * #2350 https://leetcode.com/problems/shortest-impossible-sequence-of-rolls/
 */
public class ShortestImpossibleSequenceOfRolls {

    // time O(n), space O(k)
    public int shortestSequence(int[] rolls, int k) {
        int res = 1;
        Set<Integer> set = new HashSet<>();
        for (int r : rolls) {
            set.add(r);
            if (set.size() == k) {
                res++;
                set = new HashSet<>();
            }
        }
        return res;
    }
}
