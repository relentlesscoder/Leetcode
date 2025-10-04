package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3185 https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/
 */
public class CountPairsThatFormACompleteDayII {

    // time O(n), space O(1)
    public long countCompleteDayPairs(int[] hours) {
        int[] map = new int[24];
        long res = 0;
        for (int hour : hours) {
            int mod = hour % 24, key = (24 - mod) % 24;
            res += map[key];
            map[mod]++;
        }
        return res;
    }
}
