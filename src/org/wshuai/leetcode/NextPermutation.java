package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2016.
 * #0031 https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {

	// time O(n), space O(1)
	// https://www.geeksforgeeks.org/lexicographic-permutations-of-string/
	public void nextPermutation(int[] nums) {
		if (nums.length <= 1) {
			return;
		}
		int n = nums.length, left = n - 2;
		// Take the previously printed permutation and find the rightmost character in it
		// which is smaller than its next character. Let us call this character as ‘left character’.
		while (left >= 0 && nums[left] >= nums[left + 1]) {
			left--;
		}
		if (left != -1) {
			// Now find the ceiling of the ‘left character’. Ceiling is the smallest character on right
			// of ‘left character’, which is greater than ‘left character’. Let us call the ceil character
			// as ‘right character’.
			int right = n - 1, val = nums[left];
			while (left < right && nums[right] <= val) {
				right--;
			}
			// Swap the two characters.
			nums[left] = nums[right];
			nums[right] = val;
		}
		// Sort the substring (in non-decreasing order) after the original index of ‘left character’.
		int start = left + 1, end = n - 1;
		while (start < end) {
			int temp = nums[start];
			nums[start++] = nums[end];
			nums[end--] = temp;
		}
	}
}
