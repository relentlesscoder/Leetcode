package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/27/2023.
 * #1980 https://leetcode.com/problems/find-unique-binary-string/
 */
public class FindUniqueBinaryString {

	// time O(n), space O(n)
	public String findDifferentBinaryString(String[] nums) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			sb.append(nums[i].charAt(i) == '0' ? '1' : '0'); // simply OMG!!!
		}
		return sb.toString();
	}

	// time O(n * log(n)), space O(n * log(n))
	public String findDifferentBinaryStringBinarySearch(String[] nums) {
		Arrays.sort(nums);
		int n = nums.length, low = 0, high = n - 1, res = 0;
		while (low < high) { // find the last number that matches its index, meaning the next number from it is missing
			int mid = (high + low + 1) >> 1;
			if (mid >= convertToInteger(nums[mid])) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		res = low < convertToInteger(nums[low]) ? 0 : low + 1; // need to check if the first number is already missing
		return convertToBinaryString(res, n);
	}

	private int convertToInteger(String s) {
		int res = 0;
		for (int i = s.length() - 1, j = 1; i >= 0; i--, j <<= 1) {
			res += j * (s.charAt(i) - '0');
		}
		return res;
	}

	private String convertToBinaryString(int val, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append("" + val % 2);
			val /= 2;
		}
		return sb.reverse().toString();
	}
}
