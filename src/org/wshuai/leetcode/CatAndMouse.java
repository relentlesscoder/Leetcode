package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/23/2019.
 * #913 https://leetcode.com/problems/cat-and-mouse/
 */
public class CatAndMouse {
	private int[][][] dp;

	// https://www.acwing.com/solution/leetcode/content/556/
	public int catMouseGame(int[][] graph) {
		int n = graph.length;
		dp = new int[2 * n][n][n];
		for(int i = 0; i < dp.length; i++){
			for(int j = 0; j < dp[0].length; j++){
				Arrays.fill(dp[i][j], -1);
			}
		}
		return dfs(graph, 0, 1, 2);
	}

	private int dfs(int[][] graph, int step, int mouse, int cat){
		if(step == graph.length * 2){
			return 0;
		}
		if(mouse == cat){
			return dp[step][mouse][cat] = 2;
		}
		if(mouse == 0){
			return dp[step][mouse][cat] = 1;
		}
		if(dp[step][mouse][cat] != -1){
			return dp[step][mouse][cat];
		}
		int p = step % 2;
		boolean flag;
		// mouse's turn
		if(p == 0){
			// by default, cat wins
			flag = true;
			// if there is one way that mouse can win, mouse will win
			// otherwise, cat will win
			for(int i = 0; i < graph[mouse].length; i++){
				int nxt = dfs(graph, step + 1, graph[mouse][i], cat);
				// if there is one way that mouse can win, mouse will win because he plays optimally
				if(nxt == 1){
					return dp[step][mouse][cat] = 1;
				}else if(nxt != 2){// if mouse cannot win but can get a draw then he will try get the draw
					flag = false;
				}
			}
			dp[step][mouse][cat] = flag ? 2 : 0;
		}else{
			// by default, mouse wins
			flag = true;
			for(int i = 0; i < graph[cat].length; i++){
				if(graph[cat][i] != 0){
					int nxt = dfs(graph, step + 1, mouse, graph[cat][i]);
					if(nxt == 2){
						return dp[step][mouse][cat] = 2;
					}else if(nxt != 1){
						flag = false;
					}
				}
			}
			dp[step][mouse][cat] = flag ? 1 : 0;
		}
		return dp[step][mouse][cat];
	}
}
