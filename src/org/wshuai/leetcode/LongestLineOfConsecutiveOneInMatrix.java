package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2019.
 * #0562 https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
 */
public class LongestLineOfConsecutiveOneInMatrix {
	// time O(m*n)
	public int longestLine(int[][] M) {
		if(M == null || M.length == 0 || M[0].length == 0){
			return 0;
		}
		int res = 0, m = M.length, n = M[0].length;
		// horizontal
		for(int i = 0; i < m; i++){
			int count = 0;
			for(int j = 0; j < n; j++){
				if(M[i][j] == 0){
					count = 0;
				}else{
					res = Math.max(res, ++count);
				}
			}
		}
		// vertical
		for(int j = 0; j < n; j++){
			int count = 0;
			for(int i = 0; i < m; i++){
				if(M[i][j] == 0){
					count = 0;
				}else{
					res = Math.max(res, ++count);
				}
			}
		}
		// upper diagonal
		for(int k = 0; k < n; k++){
			int count = 0;
			for(int i = 0, j = k; i < m && j < n; i++, j++){
				if(M[i][j] == 0){
					count = 0;
				}else{
					res = Math.max(res, ++count);
				}
			}
		}
		// lower diagonal
		for(int k = 0; k < m; k++){
			int count = 0;
			for(int i = k, j = 0; i < m && j < n; i++, j++){
				if(M[i][j] == 0){
					count = 0;
				}else{
					res = Math.max(res, ++count);
				}
			}
		}
		// upper anti-diagonal
		for(int k = n - 1; k >= 0; k--){
			int count = 0;
			for(int i = 0, j = k; i < m && j >= 0; i++, j--){
				if(M[i][j] == 0){
					count = 0;
				}else{
					res = Math.max(res, ++count);
				}
			}
		}
		// lower anti-diagonal
		for(int k = 0; k < m; k++){
			int count = 0;
			for(int i = k, j = n - 1; i < m && j >= 0; i++, j--){
				if(M[i][j] == 0){
					count = 0;
				}else{
					res = Math.max(res, ++count);
				}
			}
		}
		return res;
	}
}
