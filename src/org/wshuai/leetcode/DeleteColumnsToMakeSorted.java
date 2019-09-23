package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #944 https://leetcode.com/problems/delete-columns-to-make-sorted/
 */
public class DeleteColumnsToMakeSorted {
	public int minDeletionSize(String[] A) {
		int len = A[0].length();
		int res = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < A.length - 1; j++) {
				if (A[j].charAt(i) > A[j + 1].charAt(i)) {
					res++;
					break;
				}
			}
		}
		return res;
	}
}
