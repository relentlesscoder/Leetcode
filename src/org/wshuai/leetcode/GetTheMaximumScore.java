package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2020.
 * #1537 https://leetcode.com/problems/get-the-maximum-score/
 */
public class GetTheMaximumScore {

	private static long MOD = 1_000_000_007;

	// time O(n)
	public int maxSum(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length, i = 0, j = 0;
		long res = 0, sum1 = 0, sum2 = 0;
		while(i < m || j < n){
			int v1 = i == m ? Integer.MAX_VALUE : nums1[i];
			int v2 = j == n ? Integer.MAX_VALUE : nums2[j];
			if(v1 == v2){
				res += Math.max(sum1, sum2);
				i++;
				j++;
				sum1 = sum2 = v1;
			}else if(v1 < v2){
				sum1 += v1;
				i++;
			}else{
				sum2 += v2;
				j++;
			}
		}
		res += Math.max(sum1, sum2);
		return (int)(res % MOD);
	}
}
