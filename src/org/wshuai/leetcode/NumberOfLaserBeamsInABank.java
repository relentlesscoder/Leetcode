package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2023.
 * #2125 https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
 */
public class NumberOfLaserBeamsInABank {

	// time O(n * m), space O(1)
	public int numberOfBeams(String[] bank) {
		int res = 0, prev = 0;
		for (int i = 0; i < bank.length; i++) {
			int curr = 0;
			for (char c : bank[i].toCharArray()) {
				if (c == '1') {
					curr++;
				}
			}
			if (curr > 0) {
				res += curr * prev;
				prev = curr;
			}
		}
		return res;
	}
}
