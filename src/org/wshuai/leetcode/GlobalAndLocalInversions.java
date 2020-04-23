package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0775 https://leetcode.com/problems/global-and-local-inversions/
 */
public class GlobalAndLocalInversions {

	// time O(n)
	// https://leetcode.com/problems/global-and-local-inversions/discuss/150991/Logical-Thinking-with-Clear-Code
	public boolean isIdealPermutation(int[] A) {
		int cmax = 0;
		// the only possible permutation satisfy the condition
		// that the number of local inversions equals to that
		// of global inversions is:
		// 0 1 3 2 4 5 7 6
		for(int i = 0; i < A.length; i++){
			if (Math.abs(i - A[i]) > 1){
				return false;
			}
		}
		return true;
	}
}
