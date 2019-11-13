package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/19.
 * #775 https://leetcode.com/problems/global-and-local-inversions/
 */
public class GlobalAndLocalInversions {

	/*
	If the number of global inversions is equal to the number of local inversions,
	it means that all global inversions in permutations are local inversions.
	It also means that we can not find A[i] > A[j] with i+2<=j.
	In other words, max(A[i]) < A[i+2]
	*/
	public boolean isIdealPermutation(int[] A) {
		int cmax = 0;
		for(int i = 0; i < A.length - 2; i++){
			cmax = Math.max(cmax, A[i]);
			if(cmax > A[i + 2]){
				return false;
			}
		}
		return true;
	}
}
