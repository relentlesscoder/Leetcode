package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/08/2025.
 * #2146 https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/
 */
public class KHighestRankedItemsWithinAPriceRange {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n + k * log(k)), space O(min(m, n) + k)
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> {
            if (a[3] != b[3]) {
                return b[3] - a[3];
            } else if (a[2] != b[2]) {
                return b[2] - a[2];
            } else if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        });
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {start[0], start[1], grid[start[0]][start[1]], 0});
        grid[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], p = curr[2], d = curr[3];
            if (p >= pricing[0] && p <= pricing[1]) {
                maxQueue.offer(curr);
                if (maxQueue.size() > k) {
                    maxQueue.poll();
                }
            }
            for (int i = 0; i < 4; i++) {
                int x = r + DIRS[i], y = c + DIRS[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 0) {
                    queue.offer(new int[] {x, y, grid[x][y], d + 1});
                    grid[x][y] = 0;
                }
            }
        }
        LinkedList<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < k && !maxQueue.isEmpty(); i++) {
            int[] curr = maxQueue.poll();
            res.offerFirst(Arrays.asList(curr[0], curr[1]));
        }
        return res;
    }
}
