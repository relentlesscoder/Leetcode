package org.wshuai.leetcode;

/**
 * Created by Wei on 11/9/2019.
 * #991 https://leetcode.com/problems/broken-calculator/
 */
public class BrokenCalculator {
	public int brokenCalc(int X, int Y) {
		int res = 0;
		while (Y > X) {
			Y = Y % 2 > 0 ? Y + 1 : Y / 2;
			res++;
		}
		return res + X - Y;
	}
}
