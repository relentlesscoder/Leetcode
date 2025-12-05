package org.wshuai.leetcode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 07/10/2025.
 * #2790 https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/
 */
public class MaximumNumberOfGroupsWithIncreasingLength {

    // time O(n * log(n)), space O(1)
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        int n = usageLimits.size(), low = 1, high = n;
        Collections.sort(usageLimits, (a, b) -> b - a);
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (canCreateGroup(mid, usageLimits)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean canCreateGroup(int groups, List<Integer> usageLimits) {
        // https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/solutions/3807567/o-nlogn-binary-search-find-lower-triangular-matrix-easy-to-understand/
        int gap = 0, h = groups;
        for (int i = 0; i < usageLimits.size(); i++) {
            gap = Math.max(h - usageLimits.get(i) + gap, 0);
            if (h > 0) {
                h--; // it's possible we finished fill all columns, but we still have gap to fill
            }
        }
        return gap == 0;
    }
}
