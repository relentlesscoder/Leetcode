package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2019.
 * #1010 https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */
public class PairsOfSongsWithTotalDurationsDivisibleBySixty {

    // time O(n), space O(1)
    public int numPairsDivisibleBy60(int[] time) {
        int res = 0;
        int[] mod = new int[61];
        for (int t : time) {
            res += mod[(60 - t % 60) % 60];
            mod[t % 60]++;
        }
        return res;
    }
}
