package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/19/2026.
 * #1964
 * https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/
 */
public class FindTheLongestValidObstacleCourseAtEachPosition {

	// time O(n log n), space O(1)
	public int[] longestObstacleCourseAtEachPositionGreedy(int[] obstacles) {
		// 贪心 + 二分 #0300
		int n = obstacles.length;
		int[] res = new int[n];
		for (int i = 0, high = 0; i < n; i++) {
			int idx = binarySearch(obstacles, high, obstacles[i]);
			res[i] = idx + 1;
			if (idx == high) { // 原地修改数组
				obstacles[high++] = obstacles[i];
			} else {
				obstacles[idx] = obstacles[i];
			}
		}
		return res;
	}

	private int binarySearch(int[] nums, int high, int target) {
		int low = 0;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] <= target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
