package org.wshuai.leetcode;

/**
 * Created by Wei on 12/4/19.
 * #879 https://leetcode.com/problems/profitable-schemes/
 */
public class ProfitableSchemes {
	private final int mod = 1_000_000_007;
	private Integer[][][] dp;

	public int profitableSchemes(int G, int P, int[] group, int[] profit) {
		int N = group.length;
		dp = new Integer[N][G + 1][P + 1];
		return dfs(0, G, P, group, profit);
	}

	private int dfs(int cur, int available, int target, int[] group, int[] profit){
		if(cur == group.length){
			return target <= 0 ? 1 : 0;
		}
		int profitNeeded = Math.max(target, 0);
		if(dp[cur][available][profitNeeded] != null){
			return dp[cur][available][profitNeeded];
		}
		int res = 0;
		res = (res + dfs(cur + 1, available, target, group, profit)) % mod;
		if(available >= group[cur]){
			res = (res + dfs(cur + 1, available - group[cur], target - profit[cur], group, profit)) % mod;
		}
		dp[cur][available][profitNeeded] = res;
		return dp[cur][available][profitNeeded];
	}
}
