package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 09/27/2025.
 * #2655 https://leetcode.com/problems/find-maximal-uncovered-ranges/
 */
public class FindMaximalUncoveredRanges {

    // time O(n * log(n)), space O(n)
    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        if (ranges.length == 0) {
            return new int[][]{{0, n - 1}};
        }
        Arrays.sort(ranges, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        for (int[] range : ranges) {
            if (merged.isEmpty() || range[0] > merged.get(merged.size() - 1)[1] + 1) {
                merged.add(range);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(range[1], merged.get(merged.size() - 1)[1]);
            }
        }
        List<int[]> res = new ArrayList<>();
        if (merged.get(0)[0] != 0) {
            res.add(new int[]{0, merged.get(0)[0] - 1});
        }
        for (int i = 1; i < merged.size(); i++) {
            int[] prev = merged.get(i - 1), curr = merged.get(i);
            res.add(new int[]{prev[1] + 1, curr[0] - 1});
        }
        if (merged.get(merged.size() - 1)[1] != n - 1) {
            res.add(new int[]{merged.get(merged.size() - 1)[1] + 1, n - 1});
        }
        return res.toArray(new int[0][2]);
    }
}
