package org.wshuai.leetcode;

/**
 * Created by Wei on 09/07/2020.
 * #1573 https://leetcode.com/problems/number-of-ways-to-split-a-string/
 */
public class NumberOfWaysToSplitAString {

	private static final int MOD = 1_000_000_007;

	// time O(n)
	public int numWays(String s) {
		char[] arr = s.toCharArray();
		int ones = 0, n = arr.length;
		for(int i = 0; i < n; i++){
			ones += s.charAt(i) - '0';
		}
		if(ones == 0){
			return (int)((n - 2L) * (n - 1L) / 2 % MOD);
		}
		if(ones % 3 != 0){
			return 0;
		}
		int count = 0, oneThird = ones / 3, twoThird = (oneThird << 1),
			firstCut = 0, secondCut = 0;
		for(int i = 0; i < n; i++){
			if(s.charAt(i) == '1'){
				count++;
			}else if(count == oneThird){
				firstCut++;
			}else if(count == twoThird){
				secondCut++;
			}
		}
		return (int)((firstCut + 1L) * (secondCut + 1L) % MOD);
	}
}
