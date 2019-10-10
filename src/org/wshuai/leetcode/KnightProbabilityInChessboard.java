package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/7/2019.
 * #688 https://leetcode.com/problems/knight-probability-in-chessboard/
 */
public class KnightProbabilityInChessboard {
	private int[][] move;
	private Map<String, Double> map;

	public double knightProbability(int N, int K, int r, int c) {
		map = new HashMap<>();
		move = new int[2][8];
		move[0] = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
		move[1] = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
		return dfs(r, c, K, N);
	}

	private double dfs(int i, int j, int res, int N){
		if(!isValid(i, j, N)){
			return 0;
		}
		if(res == 0){
			return 1;
		}
		String key = i + "," + j + "," + res;
		if(map.containsKey(key)){
			return map.get(key);
		}
		double rate = 0;
		for(int k = 0; k < 8; k++){
			int x = i + move[0][k];
			int y = j + move[1][k];
			rate += 0.125 * dfs(x, y, res - 1, N);
		}
		map.put(key, rate);
		return rate;
	}

	private boolean isValid(int i, int j, int N){
		return i >= 0 && j >= 0 && i < N && j < N;
	}
}
