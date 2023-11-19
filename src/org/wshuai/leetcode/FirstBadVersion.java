package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0278 https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {

	// time O(log(n)), space O(1)
	public int firstBadVersion(int n) {
		int left = 1, right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// dummy method
	private boolean isBadVersion(int mid) {
		return false;
	}
}
