package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #0264 https://leetcode.com/problems/ugly-number-ii/
 */
public class UglyNumberII {
	// time O(n)
	// https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
	public int nthUglyNumber(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		int i = 0, j = 0, k = 0;
		int f2 = 2, f3 = 3, f5 = 5;
		for(int x = 1; x < n; x++){
			int min = Math.min(f2, Math.min(f3, f5));
			dp[x] = min;
			if(f2 == min){
				f2 = 2 * dp[++i];
			}
			if(f3 == min){
				f3 = 3 * dp[++j];
			}
			if(f5 == min){
				f5 = 5 * dp[++k];
			}
		}
		return dp[n - 1];
	}
}
