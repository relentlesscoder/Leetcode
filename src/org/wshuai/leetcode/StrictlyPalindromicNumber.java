package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/12/2023.
 * #2396 https://leetcode.com/problems/strictly-palindromic-number/
 */
public class StrictlyPalindromicNumber {

	// time O(n * log(n)), space O(n * log(n))
	public boolean isStrictlyPalindromic(int n) {
		for (int i = 2; i < n - 1; i++) {
			if (!isPalindrome(toBase(n, i))) {
				return false;
			}
		}
		return true;
	}

	private boolean isPalindrome(int[] arr) {
		int left = 0, right = arr.length - 1;
		while (left < right) {
			if (arr[left] != arr[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	private int[] toBase(int n, int base) {
		int remaining = n;
		List<Integer> digits = new ArrayList<>();
		while (remaining > 0) {
			digits.add(remaining % base);
			remaining = remaining / base;
		}
		int[] res = new int[digits.size()];
		for (int i = 0; i < digits.size(); i++) {
			res[i] = digits.get(digits.size() - i - 1);
		}
		return res;
	}
}
