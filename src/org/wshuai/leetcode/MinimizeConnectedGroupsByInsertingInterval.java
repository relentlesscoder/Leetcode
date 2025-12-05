package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/05/2025.
 * #3323 https://leetcode.com/problems/minimize-connected-groups-by-inserting-interval/
 */
public class MinimizeConnectedGroupsByInsertingInterval {

    // time O(n * log(n)), space O(n)
    public int minConnectedGroupsTwoPointers(int[][] intervals, int k) {
        int n = intervals.length, res = n;
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                int[] tail = merged.get(merged.size() - 1);
                tail[1] = Math.max(tail[1], interval[1]);
            }
        }
        int m = merged.size();
        for (int i = 0, j = 0; i < m; i++) {
            int end = merged.get(i)[1] + k;
            while (j < m && merged.get(j)[0] <= end) {
                j++;
            }
            res = Math.min(res, m - j + i + 1);
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int minConnectedGroupsBinarySearch(int[][] intervals, int k) {
        int n = intervals.length, res = n;
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                int[] tail = merged.get(merged.size() - 1);
                tail[1] = Math.max(tail[1], interval[1]);
            }
        }
        int m = merged.size();
        for (int i = 0; i < m; i++) {
            int index = binarySearch(merged, i, m - 1, merged.get(i)[1] + k);
            res = Math.min(res, m - index + i);
        }
        return res;
    }

    private int binarySearch(List<int[]> merged, int low, int high, int target) {
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (merged.get(mid)[0] > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }
}
