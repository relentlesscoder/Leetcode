package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2019.
 * #1252 https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
 */
public class CellsWithOddValuesInAMatrix {
	public int oddCells(int n, int m, int[][] indices) {
		int[] rows = new int[n];
		int[] cols = new int[m];
		for(int[] p : indices){
			rows[p[0]]++;
			cols[p[1]]++;
		}
		int res = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if((rows[i] + cols[j]) % 2 == 1){
					res++;
				}
			}
		}
		return res;
	}
}
