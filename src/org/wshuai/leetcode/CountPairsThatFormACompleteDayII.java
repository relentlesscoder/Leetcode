package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3185 https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/
 */
public class CountPairsThatFormACompleteDayII {

    // time O(n), space O(1)
    public long countCompleteDayPairs(int[] hours) {
        long res = 0;
        int[] count = new int[24];
        for (int h : hours) {
            res += count[(24 - h % 24) % 24];
            count[h % 24]++;
        }
        return res;
    }
}
