package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/08/2023.
 * #1944 https://leetcode.com/problems/number-of-visible-people-in-a-queue/
 */
public class NumberOfVisiblePeopleInAQueue {

	// time O(n), space O(n)
	public int[] canSeePersonsCount(int[] heights) {
		int n = heights.length;
		int[] res = new int[n];
		Arrays.fill(res, 0, n - 1, 1); // pre-fill for pairs next to each other except the last one
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) { // every people in the monotonic queue that are less than i can see the i
				res[stack.peek()] += (i - stack.peek() > 1 ? 1 : 0);
				stack.pop();
			}
			if (!stack.isEmpty()) {
				res[stack.peek()] += (i - stack.peek() > 1 ? 1 : 0); // the head of the stack can also see i
			}
			stack.push(i);
		}
		return res;
	}
}
