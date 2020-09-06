package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/05/2020.
 * #1575 https://leetcode.com/problems/count-all-possible-routes/
 */
public class CountAllPossibleRoutes {

	private static final int MOD = 1_000_000_007;

	// time O(fuel*n^2), space O(fuel*n)
	public int countRoutes(int[] locations, int start, int finish, int fuel) {
		int n = locations.length, s = locations[start], f = locations[finish];
		Arrays.sort(locations);
		for(int i = 0; i < n; i++){
			if(locations[i] == s){
				start = i;
			}
			if(locations[i] == f){
				finish = i;
			}
		}
		long[][] dp = new long[n][fuel + 1];
		for(int i = 0; i < n; i++){
			Arrays.fill(dp[i], -1);
		}
		return (int)dfs(start, finish, locations, fuel, dp);
	}

	private long dfs(int cur, int finish, int[] locations, int fuel, long[][] dp){
		if(fuel < 0){
			return 0;
		}
		if(dp[cur][fuel] != -1){
			return dp[cur][fuel];
		}
		long res = cur == finish ? 1 : 0;
		int left = findLeftBound(locations, locations[cur] - fuel);
		int right = findRightBound(locations, locations[cur] + fuel);
		for(int i = left; i <= right; i++){
			if(i == cur){
				continue;
			}
			res = (res + dfs(i, finish, locations,
				fuel - Math.abs(locations[i] - locations[cur]), dp)) % MOD;
		}
		dp[cur][fuel] = res;
		return dp[cur][fuel];
	}

	private int findLeftBound(int[] nums, int val){
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] < val){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	private int findRightBound(int[] nums, int val){
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left + 1) / 2;
			if(nums[mid] > val){
				right = mid - 1;
			}else{
				left = mid;
			}
		}
		return left;
	}
}
