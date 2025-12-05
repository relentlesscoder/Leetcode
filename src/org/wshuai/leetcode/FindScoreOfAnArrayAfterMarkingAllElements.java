package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 08/02/2025.
 * #2593 https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/
 */
public class FindScoreOfAnArrayAfterMarkingAllElements {

    // time O(n), space O(1)
    public long findScore(int[] nums) {
        long res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i += 2) {
            int j = i;
            while (i + 1 < n && nums[i + 1] < nums[i]) {
                i++;
            }
            for (int k = i; k >= j; k -= 2) {
                res += nums[k];
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public long findScoreSorting(int[] nums) {
        long res = 0;
        int n = nums.length;
        int[][] sorted = new int[n][2];
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = new int[] {nums[i], i};
        }
        Arrays.sort(sorted, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            int[] curr = sorted[i];
            if (marked[curr[1]]) {
                continue;
            }
            res += curr[0];
            if (curr[1] > 0) {
                marked[curr[1] - 1] = true;
            }
            if (curr[1] < n - 1) {
                marked[curr[1] + 1] = true;
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public long findScoreMinQueue(int[] nums) {
        long res = 0;
        int n = nums.length;
        boolean[] marked = new boolean[n];
        PriorityQueue<int[]> minQueue = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
        );
        for (int i = 0; i < n; i++) {
            minQueue.offer(new int[] {nums[i], i});
        }
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            if (marked[curr[1]]) {
                continue;
            }
            res += curr[0];
            if (curr[1] > 0) {
                marked[curr[1] - 1] = true;
            }
            if (curr[1] < n - 1) {
                marked[curr[1] + 1] = true;
            }
        }
        return res;
    }
}
