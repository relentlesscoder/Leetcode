package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/17/2019.
 * #0768 https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
 */
public class MaxChunksToMakeSortedII {

    // time O(n), space O(n)
	public int maxChunksToSorted(int[] arr) {
		int res = 0, n = arr.length;
		int[] rightMin = new int[n + 1];
		rightMin[n] = Integer.MAX_VALUE;
		for (int i = n - 1; i >= 0; i--) {
			rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
		}
		for (int i = 0, leftMax = Integer.MIN_VALUE; i < n; i++) {
			leftMax = Math.max(leftMax, arr[i]);
			// If rightMin[i + 1] >= max, add one more chunk
			if (leftMax <= rightMin[i + 1]) {
				res++;
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public int maxChunksToSortedMonotonicStack(int[] arr) {
		int n = arr.length;
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0, max = Integer.MIN_VALUE; i < n; i++) {
			// If arr[i] >= max, add one more chunk candidate to the queue
			if (arr[i] >= max) {
				max = arr[i];
				stack.push(arr[i]);
			} else {
				// Otherwise need to merge arr[i] into previous chunks with
				// max > arr[i]
				// e.g. [1,2,0,0,2,3] index 2 and 3
				while (!stack.isEmpty() && stack.peek() > arr[i]) {
					stack.pop();
				}
				// Enqueue the max back to maintain current chunk max
				stack.push(max);
			}
		}
		return stack.size();
	}
}
