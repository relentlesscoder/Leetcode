package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0012 https://leetcode.com/problems/integer-to-roman/
 */
public class IntegerToRoman {
	public String intToRoman(int num) {
		String[][] map = new String[][]{
			{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
			{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
			{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
			{"", "M", "MM", "MMM" }
		};
		return map[3][num / 1000]
			+ map[2][(num % 1000) / 100]
			+ map[1][(num % 100) / 10]
			+ map[0][num % 10];
	}
}
