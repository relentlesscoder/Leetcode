package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/21/2019.
 * #0668 https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 */
public class KthSmallestNumberInMultiplicationTable {

	// time O(m * log(m * n)), space O(1)
	public int findKthNumber(int m, int n, int k) {
		int low = 1, high = m * n;
		while (low < high) {
			int mid = (low + high) >> 1;
			if (hasEnoughNumbers(k, m, n, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	/*
	1*1, 1*2, 1*3, ... 1*n
	2*1, 2*2, 2*3, ... 2*n
	3*1, 3*2, 3*3, ... 3*n
	...
	for row i, number of elements less than or equal to v is v/i
	*/
	private boolean hasEnoughNumbers(int k, int m, int n, int threshold) {
		int total = 0;
		for (int i = 1; i <= m; i++) {
			int count = Math.min(n, threshold / i);
			if (count == 0) {
				break;
			}
			total += count;
			if (total >= k) {
				return true;
			}
		}
		return false;
	}

	// time O(n * m * log(m)), space O(m)
	public int findKthNumberMinQueue(int m, int n, int k) {
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for (int i = 1; i <= m; i++) {
			minQueue.offer(new int[]{i, i});
		}
		int[] curr = null;
		for (int i = 0; i < k; i++) {
			curr = minQueue.poll();
			int next = curr[0] + curr[1];
			if (next <= curr[1] * n) {
				minQueue.offer(new int[]{next, curr[1]});
			}
		}
		return curr[0];
	}
}
