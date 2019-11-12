package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2019.
 * #562 https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
 */
public class LongestLineOfConsecutiveOneInMatrix {
	public int longestLine(int[][] M) {
		int r = M.length;
		if(r == 0){
			return 0;
		}
		int c = M[0].length;
		int res = 0;

		// horizontal
		for(int i = 0; i < r; i++){
			int count = 0;
			for(int j = 0; j < c; j++){
				if(M[i][j] == 1){
					count++;
					res = Math.max(res, count);
				}else{
					count = 0;
				}
			}
		}

		//vertical
		for(int j = 0; j < c; j++){
			int count = 0;
			for(int i = 0; i < r; i++){
				if(M[i][j] == 1){
					count++;
					res = Math.max(res, count);
				}else{
					count = 0;
				}
			}
		}

		//upper diagonal
		for(int i = 0; i < c || i < r; i++){
			int count = 0;
			for(int x = 0, y = i; x < r && y < c; x++, y++){
				if(M[x][y] == 1){
					count++;
					res = Math.max(res, count);
				}else{
					count = 0;
				}
			}
		}

		//lower diagonal
		for(int i = 0; i < c || i < r; i++){
			int count = 0;
			for(int x = i, y = 0; x < r && y < c; x++, y++){
				if(M[x][y] == 1){
					count++;
					res = Math.max(res, count);
				}else{
					count = 0;
				}
			}
		}

		//upper anti-diagonal
		for (int i = 0; i < c || i < r; i++) {
			int count = 0;
			for (int x = 0, y = c - i - 1; x < r && y >= 0; x++, y--) {
				if (M[x][y] == 1) {
					count++;
					res = Math.max(res, count);
				} else
					count = 0;
			}
		}

		//lower anti-diagonal
		for (int i = 0; i < c || i < r; i++) {
			int count = 0;
			for (int x = i, y = c - 1; x < r && y >= 0; x++, y--) {
				if (M[x][y] == 1) {
					count++;
					res = Math.max(res, count);
				} else
					count = 0;
			}
		}

		return res;
	}
}
