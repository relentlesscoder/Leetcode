package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0342 https://leetcode.com/problems/power-of-four/
 */
public class PowerOfFour {
	// http://www.cnblogs.com/grandyang/p/5403783.html
	// https://leetcode.com/problems/power-of-four/discuss/80457/Java-1-line-(cheating-for-the-purpose-of-not-using-loops)
	public boolean isPowerOfFour(int num) {
		return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) == num;
	}
}
