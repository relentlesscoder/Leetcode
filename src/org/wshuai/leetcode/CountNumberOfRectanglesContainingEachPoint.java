package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 11/01/2025.
 * #2250 https://leetcode.com/problems/count-number-of-rectangles-containing-each-point/
 */
public class CountNumberOfRectanglesContainingEachPoint {

    // time O(n * log(n) + m * log(m), m * log(H)), space O(m + H)
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = rectangles.length, m = points.length;
        int[] res = new int[m];
        Arrays.sort(rectangles, (a, b) -> b[0] - a[0]);
        Integer[] indexes = new Integer[m];
        Arrays.setAll(indexes, index -> index);
        Arrays.sort(indexes, (a, b) -> points[b][0] - points[a][0]);
        BIT bit = new BIT(101);
        for (int i = 0, j = 0; j < m; j++) {
            int index = indexes[j];
            while (i < n && rectangles[i][0] >= points[index][0]) {
                bit.update(rectangles[i][1] + 1);
                i++;
            }
            res[index] = i - bit.query(points[index][1]);
        }
        return res;
    }

    private static class BIT {

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

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }

    // time O((n + H * m) * log(n)), space O(n + m + H)
    public int[] countRectanglesHeightBucket(int[][] rectangles, int[][] points) {
        int m = points.length;
        int[] res = new int[m];
        // Since higher bound for height is low (H <= 100), we can create
        // a bucket to store sorted length for each height
        ArrayList<Integer>[] recs = new ArrayList[101];
        Arrays.setAll(recs, index -> new ArrayList<>());
        for (int[] r : rectangles) {
            recs[r[1]].add(r[0]);
        }
        for (int i = 1; i <= 100; i++) {
            Collections.sort(recs[i]);
        }
        for (int i = 0; i < m; i++) {
            int x = points[i][0], y = points[i][1];
            // For each height >= y, use binary search to find all length >= x
            for (int j = y; j <= 100; j++) {
                res[i] += recs[j].size() - binarySearch(recs[j], x);
            }
        }
        return res;
    }

    // time O(n * log(n) + m * log(m) + m * log(n)), space O(m + n)
    public int[] countRectanglesSortByHeight(int[][] rectangles, int[][] points) {
        int n = rectangles.length, m = points.length;
        int[] res = new int[m];
        Arrays.sort(rectangles, (a, b) -> b[1] - a[1]); // O(n * log(n))
        Integer[] indexes = new Integer[m];
        Arrays.setAll(indexes, index -> index);
        Arrays.sort(indexes, (a, b) -> points[b][1] - points[a][1]); // O(m * log(m))
        ArrayList<Integer> lens = new ArrayList<>();
        int i = 0;
        for (int index : indexes) { // O(m)
            int start = i;
            while (i < n && rectangles[i][1] >= points[index][1]) { // total O(n)
                lens.add(rectangles[i++][0]);
            }
            if (i > start) {
                Collections.sort(lens); // max O(n * log(n))
            }
            res[index] = lens.size() - binarySearch(lens, points[index][0]); // log(n)
        }
        return res;
    }

    // time O(n * log(n) + m * log(m) + m * log(H)), space O(m + H)
    public int[] countRectanglesSortByLength(int[][] rectangles, int[][] points) {
        int n = rectangles.length, m = points.length;
        int[] res = new int[m];
        Arrays.sort(rectangles, (a, b) -> b[0] - a[0]); // O(n * log(n))
        Integer[] indexes = new Integer[m];
        Arrays.setAll(indexes, index -> index);
        Arrays.sort(indexes, (a, b) -> points[b][0] - points[a][0]); // O(m * log(m))
        ArrayList<Integer> heights = new ArrayList<>();
        int i = 0;
        for (int index : indexes) { // O(m)
            int start = i;
            while (i < n && rectangles[i][0] >= points[index][0]) { // total O(n)
                heights.add(rectangles[i++][1]);
            }
            if (i > start) {
                Collections.sort(heights); // max O(H * log(H))
            }
            res[index] = heights.size() - binarySearch(heights, points[index][1]); // O(log(H))
        }
        return res;
    }

    private int binarySearch(List<Integer> lens, int target) {
        int low = 0, high = lens.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (lens.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
