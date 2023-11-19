package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2020.
 * #1551 https://leetcode.com/problems/minimum-operations-to-make-array-equal/
 */
public class MinimumOperationsToMakeArrayEqual {

	// time O(n)
	public int minOperations(int n) {
		int res = 0;
		for(int i = 1, j = (2 * n) - 1; i < j; i+=2, j-=2){
			res += (j - i) / 2;
		}
		return res;
	}
}
