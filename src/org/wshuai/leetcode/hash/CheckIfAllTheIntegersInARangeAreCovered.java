package org.wshuai.leetcode.hash;

/**
 * Created by Wei on 01/06/2024.
 * #1893 https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/
 */
public class CheckIfAllTheIntegersInARangeAreCovered {

    // time O(n), space O(n)
    public boolean isCovered(int[][] ranges, int left, int right) {
        // 差分数组应用
        int max = 0, sum = 0;
        for (int[] r : ranges) {
            max = Math.max(max, r[1]);
        }
        if (left > max || right > max) {
            return false;
        }
        int[] diff = new int[max + 2];
        for (int[] r : ranges) {
            diff[r[0]]++;
            diff[r[1] + 1]--;
        }
        for (int i = 0; i <= right; i++) {
            sum += diff[i];
            if (sum <= 0 && i >= left) {
                return false;
            }
        }
        return true;
    }
}
