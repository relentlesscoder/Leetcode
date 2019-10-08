package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/2019.
 * #868 https://leetcode.com/problems/binary-gap/
 */
public class BinaryGap {
	public int binaryGap(int N) {
		int res = 0;
		int i = 0;
		int prev = -1;
		while(i < 32){
			if((N & 1) == 1){
				if(prev != -1){
					res = Math.max(res, i - prev);
				}
				prev = i;
			}
			N = (N >> 1);
			i++;
		}
		return res;
	}
}
