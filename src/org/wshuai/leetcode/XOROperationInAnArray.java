package org.wshuai.leetcode;

/**
 * Created by Wei on 06/21/2020.
 * #1486 https://leetcode.com/problems/xor-operation-in-an-array/
 */
public class XOROperationInAnArray {
	public int xorOperation(int n, int start) {
		int res = 0;
		for(int i = 0; i < n; i++){
			res ^= start + 2 * i;
		}
		return res;
	}
}
