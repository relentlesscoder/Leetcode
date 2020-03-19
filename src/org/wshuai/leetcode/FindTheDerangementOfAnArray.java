package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #0634 https://leetcode.com/problems/find-the-derangement-of-an-array/
 */
public class FindTheDerangementOfAnArray {
	private static final long MOD = 1_000_000_007;

	// time O(n)
	// https://leetcode.com/problems/find-the-derangement-of-an-array/discuss/114008/Java-O(1)-space-details-about-how-to-getting-to-the-recurrence-relations
	// https://leetcode.com/problems/find-the-derangement-of-an-array/discuss/104981/If-you-don't-understand
	public int findDerangement(int n) {
		if(n <= 1){
			return 0;
		}
		long dp1 = 0;
		long dp2 = 1;
		for(int i = 3; i <= n; i++){
			long cur = ((i - 1) * (dp1 + dp2)) % MOD;
			dp1 = dp2;
			dp2 = cur;
		}
		return (int)dp2;
	}
}
