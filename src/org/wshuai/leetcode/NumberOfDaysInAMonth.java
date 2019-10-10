package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #1118 https://leetcode.com/problems/number-of-days-in-a-month/
 */
public class NumberOfDaysInAMonth {
	public int numberOfDays(int Y, int M) {
		int res = -1;
		switch (M) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				res = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				res = 30;
				break;
			case 2:
				if (Y % 4 == 0 && (Y % 100 != 0 || Y % 400 == 0)) {
					res = 29;
				} else {
					res = 28;
				}
				break;
			default:
				res = -1;
		}
		return res;
	}
}
