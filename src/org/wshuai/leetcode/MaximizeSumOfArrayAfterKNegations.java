package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 8/21/19.
 * #1005 https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
 */
public class MaximizeSumOfArrayAfterKNegations {
	public int largestSumAfterKNegations(int[] A, int K) {
		PriorityQueue<Integer> pos = new PriorityQueue<>();
		PriorityQueue<Integer> neg = new PriorityQueue<>();
		int sum = 0;
		for (int i : A) {
			if (i >= 0) {
				pos.offer(i);
			} else {
				neg.offer(i);
			}
		}
		int i = 0;
		while (i < K) {
			if (!neg.isEmpty()) {
				int n = neg.poll();
				n *= -1;
				pos.offer(n);
			} else {
				int p = pos.poll();
				if (p != 0) {
					p *= -1;
					neg.offer(p);
				} else {
					pos.offer(0);
				}
			}
			i++;
		}
		for (int p : pos) {
			sum += p;
		}
		for (int n : neg) {
			sum += n;
		}
		return sum;
	}
}
