package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3184 https://leetcode.com/problems/count-pairs-that-form-a-complete-day-i/
 */
public class CountPairsThatFormACompleteDayI {

    // time O(n), space O(1)
    public int countCompleteDayPairs(int[] hours) {
        int res = 0;
        int[] count = new int[24];
        for (int h : hours) {
            res += count[(24 - h % 24) % 24];
            count[h % 24]++;
        }
        return res;
    }
}
