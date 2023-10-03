package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2103 https://leetcode.com/problems/rings-and-rods/
 */
public class RingsAndRods {

	// time O(n), space O(1)
	public int countPoints(String rings) {
		int res = 0;
		int[] rods = new int[10];
		for (int i = 0; i < rings.length(); i += 2) {
			char c = rings.charAt(i);
			int index = rings.charAt(i + 1) - '0';
			rods[index] |= (1 << (c == 'R' ? 0 : c == 'G' ? 1 : 2));
		}
		for (int i = 0; i < 10; i++) {
			if (rods[i] == 7) {
				res++;
			}
		}
		return res;
	}
}
