package org.wshuai.leetcode;

/**
 * Created by Wei on 12/30/2019.
 * #1304 https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 */
public class FindNUniqueIntegersSumUpToZero {
	public int[] sumZero(int n) {
		int[] res = new int[n];
		int i = 0;
		if(n % 2 != 0){
			res[0] = 0;
			i = 1;
		}
		int x = 1;
		for(; i < n; i += 2){
			res[i] = x;
			res[i + 1] = -x;
			x++;
		}
		return res;
	}
}
