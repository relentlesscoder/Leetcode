package org.wshuai.leetcode;

/**
 * Created by Wei on 07/31/2025.
 * #3206 https://leetcode.com/problems/alternating-groups-i/
 */
public class AlternatingGroupsI {

    // time O(n), space O(1)
    public int numberOfAlternatingGroups(int[] colors) {
        int res = 0, n = colors.length;
        for (int i = 0; i < n; i++) {
            int last = i == 0 ? n - 1 : i - 1, next = i == n - 1 ? 0 : i + 1;
            res += colors[i] != colors[last] && colors[i] != colors[next] ? 1 : 0;
        }
        return res;
    }
}
