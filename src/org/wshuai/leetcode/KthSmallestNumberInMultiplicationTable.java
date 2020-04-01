package org.wshuai.leetcode;

/**
 * Created by Wei on 12/21/2019.
 * #0668 https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 */
public class KthSmallestNumberInMultiplicationTable {
	// time O(m*log(m*n))
	public int findKthNumber(int m, int n, int k) {
		int low = 1, high = m * n + 1;
		while(low < high){
			int mid = low + (high - low) / 2;
			if(lessOrEqual(m, n, mid) < k){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return low;
	}

	/*
	1*1, 1*2, 1*3, ... 1*n
	2*1, 2*2, 2*3, ... 2*n
	3*1, 3*2, 3*3, ... 3*n
	...
	for row i, number of elements less than or equal to v is v/i
	*/
	private int lessOrEqual(int m, int n, int mid){
		int res = 0;
		for(int i = 1; i <= m; i++){
			res += Math.min(mid / i, n);
		}
		return res;
	}
}
