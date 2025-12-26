package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 02/18/2020.
 * #1353 https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 */
public class MaximumNumberOfEventsThatCanBeAttended {

	// time O(n * log(n)), space O(MAX)
	public int maxEventsUnionFind(int[][] events) {
		int res = 0, n = events.length, max = 0;
		Arrays.sort(events, (a, b) -> a[1] - b[1]);
		max = events[n - 1][1];
		int[] roots = new int[max + 2];
		Arrays.setAll(roots, i -> i);
		for (int[] e : events) {
			int r = find(e[0], roots);
			if (r <= e[1]) {
				res++;
				roots[r] = r + 1;
			}
		}
		return res;
	}

	private int find(int x, int[] roots) {
		if (x != roots[x]) {
			roots[x] = find(roots[x], roots);
		}
		return roots[x];
	}

    // time O(MAX + n * log(n)), space O(n)
    public int maxEventsGreedy(int[][] events) {
        int res = 0, n = events.length, max = 0;
        for (int[] e : events) {
            max = Math.max(max, e[1]);
        }
        // 按会议开始日期排序
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // d是当前日期
        for (int d = 1, e = 0; d <= max; d++) {
            // 从堆中删除所有已经结束的会议
            while (!queue.isEmpty() && queue.peek() < d) {
                queue.poll();
            }
            // 把所有今天开始的会议的结束日期入堆
            while (e < n && events[e][0] == d) {
                queue.offer(events[e++][1]);
            }
            // 选最早结束的会议参加,注意每个会议只需要参加一天
            if (!queue.isEmpty()) {
                queue.poll();
                res++;
            }
        }
        return res;
    }
}
