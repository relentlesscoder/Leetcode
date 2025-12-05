package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #1893 https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/
 */
public class CheckIfAllTheIntegersInARangeAreCovered {

    // time O(n), space O(n)
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] map = new int[52];
        for (int[] r : ranges) {
            map[r[0]]++;
            map[r[1] + 1]--;
        }
        for (int i = 0, sum = 0; i <= right; i++) {
            sum += map[i];
            if (sum <= 0 && i >= left) {
                return false;
            }
        }
        return true;
    }
}
