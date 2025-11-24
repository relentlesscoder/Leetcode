package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 07/28/2017.
 * #0581 https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {

    // time O(n), space O(1)
    public int findUnsortedSubarray(int[] nums) {
		int n = nums.length, left = n, right = -1,
				max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		// If the array is sorted, each nums[i] should equal to the
		// current max, e.g. [1,2,3,4,5]. If nums[i] < max then it
		// is not at the correct index and will be included in the
		// shortest unsorted subarray. So we iterate the array and
		// find the maximum incorrect index which will be the right
		// boundary.
		for (int i = 0; i < n; i++) {
			max = Math.max(max, nums[i]);
			if (nums[i] < max) {
				right = i;
			}
		}
		// Use same idea to find the left boundary
		for (int i = n - 1; i >= 0; i--) {
			min = Math.min(min, nums[i]);
			if (nums[i] > min) {
				left = i;
			}
		}
		return Math.max(right - left + 1, 0);
    }

	// time O(n), space O(n)
	public int findUnsortedSubarrayMonotonicStack(int[] nums) {
		int n = nums.length, left = n, right = -1;
		Deque<Integer> stack = new ArrayDeque<>();
		// For all numbers, find the min index it should be at if the array is
		// sorted which is the left boundary for the shortest unsorted subarray
		//   e.g. [2,6,4,8,1,10,9]
		//   index 2 (4) should be at index 1
		//   index 4 (1) should be at index 0
		//     ...
		//   so 0 is the left boundary
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
				left = Math.min(left, stack.pop());
			}
			stack.push(i);
		}
		stack.clear();
		// For all numbers, find the max index it should be at if the array is
		// sorted which is the right boundary for the shortest unsorted subarray
		//   e.g. [2,6,4,8,1,10,9]
		//   index 5 (10) should be at index 6
		//     ...
		//   so 6 is the right boundary
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
				right = Math.max(right, stack.pop());
			}
			stack.push(i);
		}
		return Math.max(right - left + 1, 0);
	}
}
