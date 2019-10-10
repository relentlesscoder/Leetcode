package org.wshuai.leetcode;

/**
 * Created by Wei on 2/19/17.
 * #476 https://leetcode.com/problems/number-complement/
 */
public class NumberComplement {
	public int findComplement(int num) {
		int res = 0;
		int mask1 = 0x1;
		int mask2 = 0x80000000;
		int cnt = 0;
		for (int i = 0; i < 32; i++) {
			int d = (num << i) & mask2;
			if (d == 0) {
				cnt++;
			} else {
				break;
			}
		}
		cnt = 32 - cnt;
		for (int i = cnt - 1; i >= 0; i--) {
			int d = (num >> i) & mask1;
			res += ((~d) & mask1) << i;
		}
		return res;
	}
}
