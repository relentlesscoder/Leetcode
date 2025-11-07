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
            if (colors[i % n] == 1 - colors[(i + 1) % n]
                    && colors[(i + 1) % n] == 1 - colors[(i + 2) % n]) {
                res++;
            }
        }
        return res;
    }
}
