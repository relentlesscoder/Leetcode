package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2025.
 * #1989 https://leetcode.com/problems/maximum-number-of-people-that-can-be-caught-in-tag/
 */
public class MaximumNumberOfPeopleThatCanBeCaughtInTag {

    // time O(n), space O(1)
    public int catchMaximumAmountofPeople(int[] team, int dist) {
        int res = 0, n = team.length;
        for (int i = 0, z = 0; i < n; i++) {
            if (team[i] == 1) {
                int l = Math.max(0, i - dist);
                int r = Math.min(n - 1, i + dist);
                // 贪心的从左边找第一个能被当前的1覆盖的0
                while (z < n && (team[z] != 0 || z < l)) {
                    z++;
                }
                if (z <= r) {
                    ++res;
                    ++z;
                }
            }
        }
        return res;
    }
}
