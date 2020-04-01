package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2019.
 * #0672 https://leetcode.com/problems/bulb-switcher-ii/
 */
public class BulbSwitcherII {

	// BS
	public int flipLights(int n, int m) {
		if (m == 0) return 1;

		if (n == 1) {
			if (m >= 1) return 2;
		}

		if (n == 2) {
			if (m == 1) return 3;
			if (m >= 2) return 4;
		}

		if (n >= 3) {
			if (m == 1) return 4;
			if (m == 2) return 7;
			if (m >= 3) return 8;
		}

		return 0;
	}
}
