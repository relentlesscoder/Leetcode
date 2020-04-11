package org.wshuai.leetcode;

/**
 * Created by Wei on 07/23/2017.
 * #0598 https://leetcode.com/problems/range-addition-ii/
 */
public class RangeAdditionII {
	// time O(n)
	public int maxCount(int m, int n, int[][] ops) {
		int row = m, col = n;
		for(int[] op : ops){
			row = Math.min(row, op[0]);
			col = Math.min(col, op[1]);
		}
		return row * col;
	}
}
