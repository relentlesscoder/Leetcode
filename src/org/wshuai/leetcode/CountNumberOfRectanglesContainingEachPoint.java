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

    // time O(n * log(n) + m * log(m) + m * log(n)), space O(m + n)
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = rectangles.length, m = points.length;
        int[] res = new int[m];
        Arrays.sort(rectangles, (a, b) -> b[1] - a[1]);
        Integer[] indexes = new Integer[m];
        Arrays.setAll(indexes, index -> index);
        Arrays.sort(indexes, (a, b) -> points[b][1] - points[a][1]);
        ArrayList<Integer> lens = new ArrayList<>();
        int i = 0;
        for (int index : indexes) {
            int start = i;
            while (i < n && rectangles[i][1] >= points[index][1]) {
                lens.add(rectangles[i++][0]);
            }
            if (i > start) {
                Collections.sort(lens);
            }
            res[index] = lens.size() - binarySearch(lens, points[index][0]);
        }
        return res;
    }

    // time O((n + H * m) * log(n)), space O(n + m + H)
    public int[] countRectanglesBucket(int[][] rectangles, int[][] points) {
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
