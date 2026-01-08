package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 07/28/2020.
 * #1499 https://leetcode.com/problems/max-value-of-equation/
 */
public class MaxValueOfEquation {

	// time O(n), space O(n)
	public int findMaxValueOfEquationMonotonicQueueShort(int[][] points, int k) {
		int res = Integer.MIN_VALUE, n = points.length;
		Deque<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			int[] p1 = points[i];
			int x = p1[1] - p1[0];
			while (!queue.isEmpty() && p1[0] - points[queue.peek()[0]][0] > k) {
				queue.poll();
			}
			if (!queue.isEmpty()) {
				res = Math.max(res, p1[1] + p1[0] + queue.peek()[1]);
			}
			while (!queue.isEmpty() && queue.peekLast()[1] <= x) {
				queue.pollLast();
			}
			queue.offer(new int[] {i, x});
		}
		return res;
	}

    // time O(n), space O(n)
    public int findMaxValueOfEquationMonotonicQueue(int[][] points, int k) {
		// 公式1: yi + yj + |xi - xj|
		// 公式2: |xi - xj| <= k
		// 因为 xi < xj 所以第一个公式可以转化为:
		//   yi + yj + xj - xi
		//   yj + xj + yi - xi
		// 第二个公式可以转化为:
		//   xj - xi <= k
		// 所以可以遍历数组，对于每个点 p[j] 找到满足 xj - xi <= k 并且 yi - xi 最大值的点。
        int res = Integer.MIN_VALUE, n = points.length;
		// 维护一个单调队列，存当前窗口中 y - x 的最大值的点的索引。
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int[] p1 = points[i];
			// 因为数组是按 x 排序的， 所以队列的的移动对于 xj - xi 具有单调性。将队首不合符
			// 条件 xj - xi <= k 的点弹出。
            while (!queue.isEmpty() && p1[0] - points[queue.peek()][0] > k) {
                queue.poll();
            }
			// 计算当前的最大值
            if (!queue.isEmpty()) {
                int[] p2 = points[queue.peek()];
                res = Math.max(res, p1[1] + p1[0] + p2[1] - p2[0]);
            }
			// 维护单调队列
            while (!queue.isEmpty() &&
					points[queue.peekLast()][1] - points[queue.peekLast()][0] <= p1[1] - p1[0]) {
                queue.pollLast();
            }
            queue.offer(i);
        }
        return res;
    }
}
