package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/07/2025.
 * #3380 https://leetcode.com/problems/maximum-area-rectangle-with-point-constraints-i/
 */
public class MaximumAreaRectangleWithPointConstraintsI {

    // time O(n * log(n)), space O(n)
    public int maxRectangleArea(int[][] points) {
        int n = points.length;
        int[] yCoord = new int[n];
        Arrays.setAll(yCoord, i -> points[i][1]);
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]); // O(n * log(n))
        Arrays.sort(yCoord); // O(n * log(n))

        int res = -1;
        BIT bit = new BIT(n);
        bit.update(binarySearch(yCoord, points[0][1]) + 1); // O(log(n))
        // pre[i][2] is total number of points with x <= x1 and y in [y1, y2]
        int[][] pre = new int[n][3];
        for (int i = 1; i < n; i++) { // O(n)
            int x1 = points[i - 1][0];
            int y1 = points[i - 1][1];
            int x2 = points[i][0];
            int y2 = points[i][1];
            int index = binarySearch(yCoord, y2); // O(log(n))
            bit.update(index + 1); // O(log(n))
            if (x1 != x2) { // Two points are not on the same line
                continue;
            }
            int curr = bit.query(binarySearch(yCoord, y1) + 1, index + 1); // O(log(n))
            int[] p = pre[index];
            // p[2] > 0: points pair already seen
            // p[1] == y1: points pair has y1 and y2 as y coordinates
            // p[2] + 2 == curr: no points found with y between y1 and y2
            if (p[2] > 0 && p[1] == y1 && p[2] + 2 == curr) {
                res = Math.max(res, (x2 - p[0]) * (y2 - y1));
            }
            p[0] = x1; // Previous x
            p[1] = y1; // Previous y1, y2 can be inferred from index
            p[2] = curr; // Number of points in [y1, y2]
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index) {
            while (index < tree.length) {
                tree[index]++;
                index += index & -index;
            }
        }

        public int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public int query(int left, int right) {
            return pre(right) - pre(left - 1);
        }
    }
}
