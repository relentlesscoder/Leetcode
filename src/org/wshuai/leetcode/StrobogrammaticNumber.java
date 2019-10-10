package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 * #246 https://leetcode.com/problems/strobogrammatic-number/
 */
public class StrobogrammaticNumber {
	public boolean isStrobogrammatic(String num) {
		if (num == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		boolean r = true;
		int len = num.length();
		char[] arr = num.toCharArray();
		int left = 0;
		int right = len - 1;
		while (left <= right) {
			char lVal = arr[left];
			char rVal = arr[right];
			left++;
			right--;
			if (lVal == '1' && rVal == '1') {
				continue;
			} else if (lVal == '0' && rVal == '0') {
				continue;
			} else if (lVal == '8' && rVal == '8') {
				continue;
			} else if (lVal == '6' && rVal == '9') {
				continue;
			} else if (lVal == '9' && rVal == '6') {
				continue;
			} else {
				return false;
			}
		}

		return r;
	}
}
