package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 11/07/2019.
 * #0962 https://leetcode.com/problems/maximum-width-ramp/
 */
public class MaximumWidthRamp {

    // time O(n), space O(n)
    public int maxWidthRamp(int[] nums) {
        int res = 0, n = nums.length;
		// Find maximum values on right (including itself) for
		// each number:
		// e.g. for input [6,0,8,2,1,5] -> [8,8,8,5,5,5]
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
		// Iterate array from left to right
        for (int i = 0, j = 0; j < n; j++) {
			// If current left nums[i] > rightMax[j], we can't find
			// a number on right of j that satisfies nums[j] >= nums[i]
			// so we advance i until nums[i] <= rightMax[j]
			// e.g. for input [6,0,8,2,1,5] -> [8,8,8,5,5,5],
			// index 0 (6) needs to be excluded when j is at index 3 so
			// we advance i to 1 (0). Now we can extend the right side
			// to index 5 (5) to form a ramp.
            while (i < j && nums[i] > rightMax[j]) {
                i++;
            }
            res = Math.max(res, j - i);
        }
        return res;
    }

	// time O(n), space O(n)
	public int maxWidthRampMonotonicStack(int[] nums) {
		int res = 0, n = nums.length;
		// Maintain a monotonic decreasing stack from left to right
		//   e.g. [6,2,8,1,0,5,9]
		//   stack [0,1,3,4]
		// We don't need index 2 (8) since we have a smaller value
		// at index 0 (6) that can form a better ramp given the same
		// value on right at index 6 (9).
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
				stack.push(i);
			}
		}
		for (int j = n - 1; j >= 0; j--) {
			while (!stack.isEmpty() && nums[stack.peek()] <= nums[j]) {
				res = Math.max(res, j - stack.pop());
			}
		}
		return res;
	}

    // time O(n * log(n)), space O(n)
    public int maxWidthRampSorting(int[] nums) {
        int res = 0, n = nums.length;
        // Sort index by value
        // e.g. for input [6,0,8,2,1,5]
        // sorted indexes is [1,4,3,5,0,2]
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);
        for (int i = n - 2, max = idx[n - 1]; i >= 0; i--) {
            // After sorting, the value for index on right is
            // always grater than that for index on left, so
            // we just need to calculate the max gap - for each
            // index i, the max gap should be max for all indexes
            // from it's right minus i:
            //   max - idx[i]
            if (max >= idx[i]) { // Need to make sure max >= idx[i]
                res = Math.max(res, max - idx[i]);
            }
            max = Math.max(max, idx[i]);
        }
        return res;
    }
}
