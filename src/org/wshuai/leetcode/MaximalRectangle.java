package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/05/2016.
 * #0085 https://leetcode.com/problems/maximal-rectangle/
 */
public class MaximalRectangle {

    // time O(m * n), space O(n)
    public int maximalRectangle(char[][] matrix) {
        // Use idea from #0084 to calculate rectangle with
        // maximum area for each row as the bottom
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[] row = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If matrix[i][j] is 0, it can't extend height
                // from previous rows.
                //   e.g. [["0","1"],["1","0"]]
                int val = matrix[i][j] - '0';
                row[j] = val == 0 ? 0 : row[j] + val;
            }
            res = Math.max(res, maxRectangleArea(row));
        }
        return res;
    }

    private int maxRectangleArea(int[] nums) {
        int res = 0, n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int right = 0; right <= n; right++) {
            int height = right == n ? -1 : nums[right];
            while (stack.size() > 1 && nums[stack.peek()] >= height) {
                int curr = stack.pop();
                int left = stack.peek();
                res = Math.max(res, nums[curr] * (right - left - 1));
            }
            stack.push(right);
        }
        return res;
    }

    // time O(m * n), space O(n)
    public int maximalRectangleDP(char[][] matrix) {
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
        for (int i = 0; i < r; i++) {
            int curLeft = 0, curRight = c;
            for (int j = 0; j < c; j++) {
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            for (int j = c - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = c;
                    curRight = j;
                }
            }
            for (int j = 0; j < c; j++) {
                res = Math.max(res, height[j] * (right[j] - left[j]));
            }
        }
        return res;
    }
}
