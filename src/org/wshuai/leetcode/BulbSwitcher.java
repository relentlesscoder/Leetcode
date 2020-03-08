package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0319 https://leetcode.com/problems/bulb-switcher/
 */
public class BulbSwitcher {
	// time O(log(n))
	// https://leetcode.com/problems/bulb-switcher/discuss/77112/Share-my-o(1)-solution-with-explanation
	public int bulbSwitch(int n) {
		return (int) Math.sqrt(n);
	}
}
