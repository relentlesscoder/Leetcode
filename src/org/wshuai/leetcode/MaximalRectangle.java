package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 10/05/2016.
 * #0085 https://leetcode.com/problems/maximal-rectangle/
 */
public class MaximalRectangle {
	// time O(r*c), space O(c), 24ms
	public int maximalRectangle(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = 0, r = matrix.length, c = matrix[0].length;
		int[] heights = new int[c];
		for(int i = 0; i < r; i++){
			// treat each row as a histogram
			// then it is the same as
			// #84 largest rectangle in histogram
			for(int j = 0; j < c; j++){
				if(matrix[i][j] == '0'){
					heights[j] = 0;
				}else{
					heights[j]++;
				}
			}
			res = Math.max(res, maxArea(heights));
		}
		return res;
	}

	private int maxArea(int[] heights){
		int res = 0, n = heights.length;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i <= n; i++){
			int h = (i == n ? 0 : heights[i]);
			if(stack.isEmpty() || h >= heights[stack.peek()]){
				stack.push(i);
			}else{
				res = Math.max(res, heights[stack.pop()] * (stack.isEmpty() ? i : i - stack.peek() - 1));
				i--;
			}
		}
		return res;
	}

	// time O(r*c), space O(c), 4ms
	public int maximalRectangleDP(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		/*
		matrix
		0 0 0 1 0 0 0
		0 0 1 1 1 0 0
		0 1 1 1 1 1 0

		height
		0 0 0 1 0 0 0
		0 0 1 2 1 0 0
		0 1 2 3 2 1 0

		left
		0 0 0 3 0 0 0
		0 0 2 3 2 0 0
		0 1 2 3 2 1 0

		right
		7 7 7 4 7 7 7
		7 7 5 4 5 7 7
		7 6 5 4 5 4 7

		result
		0 0 0 1 0 0 0
		0 0 3 2 3 0 0
		0 5 6 3 6 5 0
		 */
		int r = matrix.length, c = matrix[0].length, res = 0;
		int[] left = new int[c], right = new int[c], height = new int[c];
		Arrays.fill(right, c);
		for(int i = 0; i < r; i++){
			int curLeft = 0, curRight = c;
			for(int j = 0; j < c; j++){
				height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
			}
			for(int j = 0; j < c; j++){
				if(matrix[i][j] == '1'){
					left[j] = Math.max(left[j], curLeft);
				}else{
					left[j] = 0;
					curLeft = j + 1;
				}
			}
			for(int j = c - 1; j >= 0; j--){
				if(matrix[i][j] == '1'){
					right[j] = Math.min(right[j], curRight);
				}else{
					right[j] = c;
					curRight = j;
				}
			}
			for(int j = 0; j < c; j++){
				res = Math.max(res, height[j] * (right[j] - left[j]));
			}
		}
		return res;
	}
}
