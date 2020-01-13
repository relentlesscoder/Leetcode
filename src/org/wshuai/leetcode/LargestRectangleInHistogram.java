package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/04/2016.
 * #0084 https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {
	// time O(n), space O(n)
	// https://www.youtube.com/watch?v=ZmnqCZp9bBs
	// https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28900/O(n)-stack-based-JAVA-solution
	public int largestRectangleArea(int[] heights) {
		if(heights == null || heights.length == 0){
			return 0;
		}
		int res = 0;
		int n = heights.length;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i <= n; i++){
			int h = i == n ? 0 : heights[i];
			if(stack.isEmpty() || heights[stack.peek()] <= h){
				stack.push(i);
			}else{
				int top = stack.pop();
				res = Math.max(res, heights[top] * (stack.isEmpty() ? i : i - stack.peek() - 1));
				i--;
			}
		}
		return res;
	}
}
