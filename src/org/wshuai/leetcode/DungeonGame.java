package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/07/2017.
 * #0174 https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {
	// time O(r*c), space O(r*c)
	// https://leetcode.com/problems/dungeon-game/discuss/52774/C%2B%2B-DP-solution
	public int calculateMinimumHP(int[][] dungeon) {
		if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
			return 0;
		}
		int r = dungeon.length, c = dungeon[0].length;
		int[][] dp = new int[r + 1][c + 1];
		for(int i = 0; i <= r; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[r - 1][c] = dp[r][c - 1] = 1;
		for(int i = r - 1; i >= 0; i--){
			for(int j = c - 1; j >= 0; j--){
				int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
				dp[i][j] = need <= 0 ? 1 : need;
			}
		}
		return dp[0][0];
	}
}
