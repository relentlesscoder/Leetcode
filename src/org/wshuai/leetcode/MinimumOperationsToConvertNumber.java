package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/26/2023.
 * #2059 https://leetcode.com/problems/minimum-operations-to-convert-number/
 */
public class MinimumOperationsToConvertNumber {

	// time O(n * m), space O(n * m)
	public int minimumOperations(int[] nums, int start, int goal) {
		int operations = 0;
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		boolean[] visited = new boolean[1_001];
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int curr = queue.poll();
				if (curr == goal) {
					return operations;
				}
				if (curr < 0 || curr > 1_000 || visited[curr]) {
					continue;
				}
				visited[curr] = true;
				for (int num : nums) {
					int[] next = new int[] { curr + num, curr - num, curr ^ num };
					for (int d : next) {
						queue.offer(d);
					}
				}
			}
			operations++;
		}
		return -1;
	}
}
