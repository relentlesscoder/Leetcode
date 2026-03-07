package org.wshuai.leetcode.other;

/**
 * Created by Wei on 01/02/2026.
 * #LCCI-16.10 https://leetcode.cn/problems/living-people-lcci/
 */
public class LivingPeopleLCCI {

    // time O(n + MAX), space O(MAX)
    public int maxAliveYear(int[] birth, int[] death) {
        // 差分数组应用
        int res = 0, cnt = 0, n = birth.length, max = 0;
        for (int d : death) {
            max = Math.max(max, d);
        }
        int[] diff = new int[max + 2];
        for (int i = 0; i < n; i++) {
            diff[birth[i]]++;
            diff[death[i] + 1]--;
        }
        for (int i = 1900, sum = 0; i <= max; i++) {
            sum += diff[i];
            if (sum > cnt) {
                cnt = sum;
                res = i;
            }
        }
        return res;
    }
}
