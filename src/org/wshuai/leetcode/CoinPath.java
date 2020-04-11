package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/26/2019.
 * #0656 https://leetcode.com/problems/coin-path/
 */
public class CoinPath {
	// time O(n^2), space O(n)
	// see proof at https://leetcode.com/problems/coin-path/discuss/106291/Java-22-lines-solution-with-proof
	public List<Integer> cheapestJump(int[] A, int B) {
		if(A[0] == -1){
			return new ArrayList<>();
		}
		int N = A.length;
		int[] dp = new int[N], path = new int[N], len = new int[N];
		for(int i = 0; i < N; i++){
			dp[i] = Integer.MAX_VALUE;
			path[i] = -1;
		}
		dp[0] = 0;
		for(int i = 0; i < N; i++){
			if(A[i] == -1){
				continue;
			}
			for(int j = Math.max(0, i - B); j < i; j++){
				if(A[j] == -1){
					continue;
				}
				if(dp[j] + A[i] < dp[i] || (dp[j] + A[i] == dp[i] && len[i] < len[j] + 1)){
					dp[i] = dp[j] + A[i];
					path[i] = j;
					len[i] = len[j] + 1;
				}
			}
		}
		List<Integer> res = new ArrayList<>();
		for (int cur = N - 1; cur >= 0; cur = path[cur]){
			res.add(0, cur + 1);
		}
		return res.get(0) != 1 ? new ArrayList<>() : res;
	}
}
