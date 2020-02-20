package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0405 https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 */
public class ConvertANumberToHexadecimal {
	public String toHex(int num) {
		char[] map = new char[]{
				'0','1','2','3','4','5',
				'6','7','8','9','a','b',
				'c','d','e','f'
		};
		StringBuilder sb = new StringBuilder();
		int mask = (1 << 4) - 1;
		while(num != 0){
			sb.append(map[num & mask]);
			// unsigned right shift
			num = num >>> 4;
		}
		return sb.length() > 0 ? sb.reverse().toString() : "0";
	}
}
