package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3184 https://leetcode.com/problems/count-pairs-that-form-a-complete-day-i/
 */
public class CountPairsThatFormACompleteDayI {

    // time O(n), space O(1)
    public int countCompleteDayPairs(int[] hours) {
        int[] map = new int[24];
        int res = 0;
        for (int hour : hours) {
            int mod = hour % 24, key = (24 - mod) % 24;
            res += map[key];
            map[mod]++;
        }
        return res;
    }
}
