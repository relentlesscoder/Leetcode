package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0326 https://leetcode.com/problems/power-of-three/
 */
public class PowerOfThree {
	public boolean isPowerOfThree(int n) {
		int maxPow = (int)(Math.pow(3, (int)(Math.log(Integer.MAX_VALUE) / Math.log(3))));
		return (n > 0 && maxPow % n == 0);
	}
}
