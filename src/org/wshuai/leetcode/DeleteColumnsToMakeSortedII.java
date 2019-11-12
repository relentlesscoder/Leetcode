package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/19.
 * #955 https://leetcode.com/problems/delete-columns-to-make-sorted-ii/
 */
public class DeleteColumnsToMakeSortedII {
	public int minDeletionSize(String[] A) {
		int res = 0;
		int r = A.length;
		int c = A[0].length();
		boolean[] sorted = new boolean[r - 1];
		int i;
		for(int j = 0; j < c; j++){
			for(i = 0; i < r - 1; i++){
				if(!sorted[i] && A[i].charAt(j) > A[i + 1].charAt(j)){
					res++;
					break;
				}
			}
			if(i < r - 1){
				continue;
			}
			for(i = 0; i < r - 1; i++){
				sorted[i] |= A[i].charAt(j) < A[i + 1].charAt(j);
			}
		}
		return res;
	}
}
