package org.wshuai.leetcode;

/**
 * Created by Wei on 12/21/2019.
 * #668 https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 */
public class KthSmallestNumberInMultiplicationTable {
	/*
	1*1, 1*2, 1*3, ... 1*n
	2*1, 2*2, 2*3, ... 2*n
	3*1, 3*2, 3*3, ... 3*n
	...
	for row i, number of elements less than or equal to v is v/i
	*/
	public int findKthNumber(int m, int n, int k) {
		int left = 1;
		int right = m * n + 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(lex(m, n, mid) < k){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	private int lex(int m, int n, int mid){
		int cnt = 0;
		for(int i = 1; i <= m; i++){
			cnt += Math.min(mid / i, n);
		}
		return cnt;
	}
}
