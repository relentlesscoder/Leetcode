package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/19.
 * #1143 https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
	public int longestCommonSubsequence(String text1, String text2) {
		int r = text1.length();
		int c = text2.length();
		int[][] matrix = new int[r + 1][c + 1];
		for(int i = 0; i <= r; i++){
			matrix[i][0] = 0;
		}
		for(int j = 0; j <= c; j++){
			matrix[0][j] = 0;
		}
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(text1.charAt(i-1) == text2.charAt(j-1)){
					matrix[i][j] = matrix[i-1][j-1] + 1;
				}else{
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
				}
			}
		}
		return matrix[r][c];
	}
}
