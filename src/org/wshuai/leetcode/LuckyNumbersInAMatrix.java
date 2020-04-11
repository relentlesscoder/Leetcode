package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 03/16/2020.
 * #1380 https://leetcode.com/problems/lucky-numbers-in-a-matrix/
 */
public class LuckyNumbersInAMatrix {
	// time O(m*n), space O(m)
	public List<Integer> luckyNumbers (int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		int m = matrix.length, n = matrix[0].length;
		Set<Integer> rowMinIndex = new HashSet<>();
		for(int i = 0; i < m; i++){
			int min = 0;
			for(int j = 1; j < n; j++){
				if(matrix[i][j] < matrix[i][min]){
					min = j;
				}
			}
			rowMinIndex.add(i * n + min);
		}
		for(int j = 0; j < n; j++){
			int max = 0;
			for(int i = 1; i < m; i++){
				if(matrix[i][j] > matrix[max][j]){
					max = i;
				}
			}
			if(rowMinIndex.contains(max * n + j)){
				res.add(matrix[max][j]);
			}
		}
		return res;
	}
}
