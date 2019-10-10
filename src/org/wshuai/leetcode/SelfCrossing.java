package org.wshuai.leetcode;

/**
 * Created by Wei on 8/3/17.
 * #335 https://leetcode.com/problems/self-crossing/
 */
public class SelfCrossing {
	// Draw all the possible scenarios of all crosses
	public boolean isSelfCrossing(int[] x) {
		if (x == null || x.length <= 3) {
			return false;
		}
		for (int i = 3; i < x.length; i++) {
			if (x[i - 1] <= x[i - 3] && x[i] >= x[i - 2]) {
				return true;
			}
			if (i >= 4) {
				if (x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) {
					return true;
				}
			}
			if (i >= 5) {
				if (x[i - 2] >= x[i - 4] && x[i - 1] + x[i - 5] >= x[i - 3] && x[i] >= x[i - 2] - x[i - 4] && x[i - 1] <= x[i - 3]) {
					return true;
				}
			}
		}
		return false;
	}
}
