package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/14/2020.
 * #1663 https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
 */
public class SmallestStringWithAGivenNumericValue {

	// time O(n)
	public String getSmallestString(int n, int k) {
		char[] res = new char[n];
		Arrays.fill(res, 'a');
		k -= n;
		while(k > 0){
			if(res[n - 1] < 'z'){
				int d = 'z' - res[n - 1];
				res[n - 1] += Math.min(k, d);
				k -= Math.min(k, d);
			}
			n--;
		}
		return String.valueOf(res);
	}
}
