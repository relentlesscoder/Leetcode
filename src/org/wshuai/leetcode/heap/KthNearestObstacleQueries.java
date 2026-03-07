package org.wshuai.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/09/2026.
 * #3275 https://leetcode.com/problems/k-th-nearest-obstacle-queries/
 */
public class KthNearestObstacleQueries {

    // time O(k + n * log(k)), space O(k)
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] res = new int[n], maxQueue = new int[k];
        Arrays.fill(maxQueue, Integer.MAX_VALUE); // O(k)
        for (int i = 0; i < n; i++) { // O(n)
            int d = (queries[i][0] >= 0 ? queries[i][0] : -queries[i][0]) +
                    (queries[i][1] >= 0 ? queries[i][1] : -queries[i][1]);
            if (d < maxQueue[0]) {
                maxQueue[0] = d;
                sink(maxQueue, 0); // O(log(k))
            }
            res[i] = maxQueue[0] == Integer.MAX_VALUE ? -1 : maxQueue[0];
        }
        return res;
    }

    private void sink(int[] nums, int i) {
        int n = nums.length;
        while (2L * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] <= nums[i]) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }

    // time O(n * log(k)), space O(k)
    public int[] resultsArrayPriorityQueue(int[][] queries, int k) {
        // 维护一个大小为 k 的最大堆
        int n = queries.length;
        int[] res = new int[n];
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            int d = (queries[i][0] >= 0 ? queries[i][0] : -queries[i][0]) +
                    (queries[i][1] >= 0 ? queries[i][1] : -queries[i][1]);
            maxQueue.offer(d);
            if (maxQueue.size() > k) {
                maxQueue.poll();
            }
            res[i] = maxQueue.size() < k || maxQueue.peek() == Integer.MAX_VALUE ? -1 : maxQueue.peek();
        }
        return res;
    }
}
