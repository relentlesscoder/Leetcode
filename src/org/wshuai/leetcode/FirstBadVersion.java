package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0278 https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {

	// time O(log(n)), space O(1)
	public int firstBadVersion(int n) {
		int low = 1, high = n;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (isBadVersion(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	// dummy method
	private boolean isBadVersion(int mid) {
		return false;
	}
}
