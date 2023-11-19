package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2020.
 * #0069 https://leetcode.com/problems/sqrtx/
 */
public class SqrtX {

	// time O(log(n)), space O(1)
	public int mySqrt(int x) {
		if (x < 2) {
			return x;
		}
		int low = 0, high = x;
		while (low < high) {
			int mid = (low + high) >> 1;
			if (mid > x / mid) { // avoid overflow
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low - 1;
	}
}
