package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/19.
 * #795 https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
 */
public class NumberOfSubarraysWithBoundedMaximum {
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
		int res = 0;
		int i = 0;
		int j = 0;
		while(j < A.length){
			if(A[j] > R){
				res += countSubArr(A, L, i, j - 1);
				while(j < A.length && A[j] > R){
					j++;
				}
				i = j;
			}else{
				if(j == A.length - 1){
					res += countSubArr(A, L, i, j);
				}
				j++;
			}
		}
		return res;
	}

	private int countSubArr(int[] A, int L, int i, int j){
		int res = 0;
		int prev = 0;
		for(int k = j; k >= i; k--){
			if(A[k] < L){
				res += prev;
			}else{
				int cnt = j - k + 1;
				prev = cnt;
				res += cnt;
			}
		}
		return res;
	}
}
