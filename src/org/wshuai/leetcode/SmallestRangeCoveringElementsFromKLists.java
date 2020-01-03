package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/16/2019.
 * #632 https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
public class SmallestRangeCoveringElementsFromKLists {
	// same idea as merge sorted list
	public int[] smallestRange(List<List<Integer>> nums) {
		int k = nums.size();
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			// e = int[] {row, index, value}
			int[] e = new int[]{i, 0, nums.get(i).get(0)};
			pq.offer(e);
			max = Math.max(max, nums.get(i).get(0));
		}
		int range = Integer.MAX_VALUE;
		int start = -1, end = -1;
		while (pq.size() == k) {
			int[] curr = pq.poll();
			if (max - curr[2] < range) {
				range = max - curr[2];
				start = curr[2];
				end = max;
			}
			if (curr[1] + 1 < nums.get(curr[0]).size()) {
				curr[1] = curr[1] + 1;
				curr[2] = nums.get(curr[0]).get(curr[1]);
				pq.offer(curr);
				if (curr[2] > max) {
					max = curr[2];
				}
			}
		}

		return new int[] { start, end };
	}
}
