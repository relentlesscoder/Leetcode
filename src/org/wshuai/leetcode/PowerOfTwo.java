package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0231 https://leetcode.com/problems/power-of-two/
 */
public class PowerOfTwo {
	// time O(1)
	public boolean isPowerOfTwo(int n) {
		return n > 0 && (n & n - 1) == 0;
	}
}
