package org.wshuai.leetcode;

/**
 * Created by Wei on 04/12/2020.
 * #1411 https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
 */
public class NumberOfWaysToPaintNX3Grid {
	private static final long MOD = 1_000_000_007;

	// time O(n), space O(n)
	public int numOfWays(int n) {
		if(n == 1){
			return 12;
		}
        /*
        int[] pattern = new int[]{121, 212, 312, 123, 213, 313, 131, 231, 321, 132, 232, 323};
        Map<Integer, Integer> type = new HashMap<>(){{
            put(121, 0);
            put(212, 0);
            put(313, 0);
            put(131, 0);
            put(232, 0);
            put(323, 0);
            put(312, 1);
            put(123, 1);
            put(213, 1);
            put(231, 1);
            put(321, 1);
            put(132, 1);
        }};
        Map<Integer, List<Integer>> path = new HashMap<>(){{
            put(121, Arrays.asList(212, 312, 213, 313, 232));
            put(212, Arrays.asList(121, 123, 131, 321, 323));
            put(313, Arrays.asList(121, 131, 231, 132, 232));
            put(131, Arrays.asList(212, 312, 213, 313, 323));
            put(232, Arrays.asList(121, 123, 313, 321, 323));
            put(323, Arrays.asList(212, 131, 231, 132, 232));
            put(312, Arrays.asList(121, 123, 131, 231));
            put(123, Arrays.asList(212, 312, 231, 232));
            put(213, Arrays.asList(121, 131, 321, 132));
            put(231, Arrays.asList(312, 123, 313, 323));
            put(321, Arrays.asList(212, 213, 132, 232));
            put(132, Arrays.asList(213, 313, 321, 323));
        }};
        */
		long[][] dp = new long[2][n];
		dp[0][0] = dp[1][0] = 6;
		for(int i = 1; i < n; i++){
			dp[0][i] = (dp[0][i - 1] * 3 + dp[1][i - 1] * 2) % MOD;
			dp[1][i] = (dp[0][i - 1] * 2 + dp[1][i - 1] * 2) % MOD;
		}
		return (int)((dp[0][n - 1] + dp[1][n - 1]) % MOD);
	}
}
