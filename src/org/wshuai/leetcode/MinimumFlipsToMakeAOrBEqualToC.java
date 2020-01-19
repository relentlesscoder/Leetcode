package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1318 https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/
 */
public class MinimumFlipsToMakeAOrBEqualToC {
	public int minFlips(int a, int b, int c) {
		int x = a | b, bit1 = 0, bit2 = 0, bit3 = 0, bit4 = 0, mask = 0, res = 0;
		// 31 due to the positive requirement
		for(int i = 0; i < 31; i++){
			mask = (1 << i);
			bit1 = mask & x;
			bit2 = mask & c;
			bit3 = mask & a;
			bit4 = mask & b;
			if(bit1 == bit2){
				continue;
			}
			// bit1 = 1, bit2 = 0
			// case 1: need to flip once if only one of the ith bit of a and b is 1
			// case 2: need to flip twice if both the ith bit of a and b are 1
			if(bit1 > bit2){
				res += bit3 == bit4 ? 2 : 1;
			// bit1 = 0, bit2 = 1
			// only need to flip one of the ith bit of a and b from 0 to 1
			}else{
				res++;
			}
		}
		return res;
	}
}
