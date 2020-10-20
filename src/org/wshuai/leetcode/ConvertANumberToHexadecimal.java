package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0405 https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 */
public class ConvertANumberToHexadecimal {

	private static final char[] MAPPING = new char[]{
			'0','1','2','3','4','5',
			'6','7','8','9','a','b',
			'c','d','e','f'
	};

	// time O(log(n))
	public String toHex(int num) {
		StringBuilder sb = new StringBuilder();
		// ...00001111 to get last 4 bits
		int mask = (1 << 4) - 1;
		while(num != 0){
			sb.append(MAPPING[num & mask]);
			num = num >>> 4;
		}
		return sb.length() > 0 ? sb.reverse().toString() : "0";
	}
}
