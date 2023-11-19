package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #1891 https://leetcode.com/problems/cutting-ribbons/
 */
public class CuttingRibbons {

	// time O(n * log(max)), space O(1)
	public int maxLength(int[] ribbons, int k) {
		int low = 1, high = 0;
		for (int r : ribbons) {
			high = Math.max(high, r);
		}
		while (low < high) {
			int mid = low + (high - low + 1) / 2;
			if (canCut(ribbons, k, mid)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return (canCut(ribbons, k, low) ? low : 0);
	}

	private boolean canCut(int[] ribbons, int k, int threshold) {
		int count = 0;
		for (int i = 0; i < ribbons.length; i++) {
			count += ribbons[i] / threshold;
			if (count >= k) {
				return true;
			}
		}
		return false;
	}
}
