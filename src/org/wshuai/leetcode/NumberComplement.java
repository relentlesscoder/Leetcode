package org.wshuai.leetcode;

/**
 * Created by Wei on 02/19/2017.
 * #0476 https://leetcode.com/problems/number-complement/
 */
public class NumberComplement {
	// time O(1)
	public int findComplement(int num) {
		int msb = -1, i = 0, res = 0;
		// flip all the digits and find the msb
		// 0x0000 0000 0000 0000 0000 0000 0000 0101 ->
		// 0x1111 1111 1111 1111 1111 1111 1111 1010
		while(i < 32){
			if(((1 << i) & num) > 0){
				msb = i;
			}else{
				res = res | (1 << i);
			}
			i++;
		}
		msb++;
		// create a mask based on the msb to remove all extra leading bits
		// 0000 0000 0000 0000 0000 0000 0000 1000
		int mask = (1 << msb) - 1;
		return res & mask;
	}
}
