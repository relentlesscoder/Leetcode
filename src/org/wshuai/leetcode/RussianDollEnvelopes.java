package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/20/2017.
 * #0354 https://leetcode.com/problems/russian-doll-envelopes/
 */
public class RussianDollEnvelopes {
	// time O(n*log(n)), space O(n)
	// https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	public int maxEnvelopes(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0 || envelopes[0].length != 2){
			return 0;
		}
		int n = envelopes.length, res = 0;
		// order by width in ascending then by height in descending to break the tie
		// e.g. [3, 4], [3, 5] will create a problem but [3, 5], [3, 4] will not
		Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
		// get the LIS for height
		int[] sorted = new int[n];
		for(int i = 0; i < n; i++){
			int left = 0, right = res;
			while(left < right){
				int mid = left + (right - left) / 2;
				if(sorted[mid] < envelopes[i][1]){
					left = mid + 1;
				}else{
					right = mid;
				}
			}
			sorted[left] = envelopes[i][1];
			if(left == res){
				res++;
			}
		}
		return res;
	}

	// time O(n^2), space O(n)
	public int maxEnvelopesDP(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0 || envelopes[0].length != 2){
			return 0;
		}
		int n = envelopes.length, res = 1;
		Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
		int[] dp = new int[n];
		for(int i = 0; i < n; i++){
			dp[i] = 1;
			for(int j = 0; j < i; j++){
				if(envelopes[j][0] < envelopes[i][0]
					&& envelopes[j][1] < envelopes[i][1]){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
