package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 12/30/2019.
 * #1301 https://leetcode.com/problems/number-of-paths-with-max-score/
 */
public class NumberOfPathsWithMaxScore {
	public int[] pathsWithMaxScore(List<String> board) {
		int[][] dirs = new int[][]{
				{-1, -1}, {-1, 0}, {0, -1}
		};
		int r = board.size();
		int c = board.get(0).length();
		int[][][] dp = new int[r][c][2];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				char cur = board.get(i).charAt(j);
				if(cur == 'E'){
					dp[i][j] = new int[]{0, 1};
					continue;
				}
				if(cur == 'X'){
					dp[i][j] = new int[]{0, 0};
					continue;
				}
				int max_sum = 0;
				int path_count = 0;
				for(int k = 0; k < 3; k++){
					int x = i + dirs[k][0];
					int y = j + dirs[k][1];
					if(x < 0 || x >= r || y < 0 || y >= c || dp[x][y][1] == 0){
						continue;
					}
					if(dp[x][y][0] > max_sum){
						max_sum = dp[x][y][0];
						path_count = dp[x][y][1];
					}else if(dp[x][y][0] == max_sum){
						path_count = (path_count + dp[x][y][1]) % 1_000_000_007;
					}
				}
				if(path_count == 0){
					dp[i][j] = new int[]{0, 0};
				}else{
					int sum = max_sum + (cur == 'S' ? 0 : cur - '0');
					dp[i][j] = new int[]{sum, path_count};
				}
			}
		}
		return dp[r - 1][c - 1];
	}
}
