package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2020.
 * #1513 https://leetcode.com/problems/number-of-substrings-with-only-1s/
 */
public class NumberOfSubstringsWithOnlyOnes {

	private static final int MOD = 1_000_000_007;

	public int numSub(String s) {
		int res = 0, count = 0;
		for(char c : s.toCharArray()){
			count = c == '0' ? 0 : count + 1;
			if(count > 0){
				res = (res + count) % MOD;
			}
		}
		return res;
	}
}
