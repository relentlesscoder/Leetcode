package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/26/2020.
 * #1329 https://leetcode.com/problems/sort-the-matrix-diagonally/
 */
public class SortTheMatrixDiagonally {
	// time O(m*n*log(m + n)), space O(m*n)
	public int[][] diagonalSort(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				int key = j - i;
				map.putIfAbsent(key, new PriorityQueue<>());
				map.get(key).offer(mat[i][j]);
			}
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				mat[i][j] = map.get(j - i).poll();
			}
		}
		return mat;
	}
}
