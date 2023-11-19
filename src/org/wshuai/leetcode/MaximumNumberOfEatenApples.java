package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/20/2023.
 * #1705 https://leetcode.com/problems/maximum-number-of-eaten-apples/
 */
public class MaximumNumberOfEatenApples {

	// time O(n * log(n)), space O(n)
	public int eatenApples(int[] apples, int[] days) {
		int res = 0;
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // greedily eat the apples with earliest rotten date
		for (int i = 0; i < apples.length || !queue.isEmpty() ; i++) {
			if (i < apples.length && apples[i] > 0) {
				queue.offer(new int[]{apples[i], i + days[i]});
			}
			while (!queue.isEmpty() && queue.peek()[1] <= i) {
				queue.poll();
			}
			if (!queue.isEmpty()) {
				res++;
				int[] top = queue.peek();
				if (--top[0] == 0) {
					queue.poll();
				}
			}
		}
		return res;
	}
}
