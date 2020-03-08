package org.wshuai.leetcode;

/**
 * Created by Wei on 04/08/2017.
 * #0526 https://leetcode.com/problems/beautiful-arrangement/
 */
public class BeautifulArrangement {
	// time O(n!)
	public int countArrangement(int N) {
		boolean[][] map = new boolean[N + 1][N + 1];
		for(int i = 1; i <= N; i++){
			for(int j = 1; j*j <= i; j++){
				if(i % j == 0){
					int d = i / j;
					map[i][j] = true;
					map[j][i] = true;
					map[i][d] = true;
					map[d][i] = true;
				}
			}
		}
		return dfs(1, N, map, 0);
	}

	private int dfs(int cur, int N, boolean[][] map, int used){
		if(cur > N){
			return 1;
		}
		int res = 0;
		for(int i = 1; i <= N; i++){
			int shift = i - 1;
			if(map[cur][i] && ((1 << shift) & used) == 0){
				res += dfs(cur + 1, N, map, (1 << shift) | used);
			}
		}
		return res;
	}
}
