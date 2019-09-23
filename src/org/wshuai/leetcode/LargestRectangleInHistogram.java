package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 10/4/16.
 * #84 https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}
		int i = 0;
		int max = 0;
		int len = heights.length + 1;
		Stack<Integer> stack = new Stack<Integer>();
		int[] hs = new int[len];
		hs = Arrays.copyOf(heights, len);
		hs[len - 1] = -1;
		while (i < len) {
			if (stack.isEmpty() || hs[stack.peek()] <= hs[i]) {
				stack.push(i);
				i++;
			} else {
				int idx = stack.pop();
				int w = stack.isEmpty() ? i : (i - stack.peek() - 1);
				int area = hs[idx] * w;
				max = area > max ? area : max;
			}
		}

		return max;
	}
}
