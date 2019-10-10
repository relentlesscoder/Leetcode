package org.wshuai.algorithm.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class NumberOfPathsWithGivenCostInMatrix {

	private Map<String, Integer> map;

	public int numberOfPaths(int[][] M, int C){
		map = new HashMap<>();

		int r = M.length;
		int c = M[0].length;

		int res = findNumberOfPaths(M, r - 1, c - 1, C);
		return res;
	}

	private int findNumberOfPaths(int[][] M, int i, int j, int C){
		if(C < 0){
			return 0;
		}

		if(i == 0 && j == 0){
			return C == M[i][j] ? 1 : 0;
		}
		String index = i + "," + j + "," + C;
		if(map.containsKey(index)){
			return map.get(index);
		}
		int res;
		if(i == 0){
			res = findNumberOfPaths(M, i, j - 1, C - M[i][j]);
		}else if(j == 0){
			res = findNumberOfPaths(M, i - 1, j, C - M[i][j]);
		}else{
			res = findNumberOfPaths(M, i, j - 1, C - M[i][j])
				+ findNumberOfPaths(M, i - 1, j, C - M[i][j]);
		}
		map.put(index, res);
		return res;
	}
}
