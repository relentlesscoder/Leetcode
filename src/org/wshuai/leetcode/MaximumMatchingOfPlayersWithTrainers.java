package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/06/2024.
 * #2410 https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
 */
public class MaximumMatchingOfPlayersWithTrainers {

    // time O(log(m + n)), space O(log(n))
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        int res = 0;
        Arrays.sort(players);
        Arrays.sort(trainers);
        for (int i = 0, j = 0; i < players.length && j < trainers.length; ) {
            if (players[i] <= trainers[j]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }
}
