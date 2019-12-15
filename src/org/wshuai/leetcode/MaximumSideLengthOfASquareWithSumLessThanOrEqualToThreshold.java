package org.wshuai.leetcode;

/**
 * Created by Wei on 12/15/2019.
 * #1292 https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
 */
public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
	public int maxSideLength(int[][] mat, int threshold) {
		int r = mat.length;
		int c = mat[0].length;
		int[][] sum = new int[r + 1][c + 1];
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				sum[i][j] = mat[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
			}
		}
		int low = 0;
		int high = Math.min(r, c);
		while(low < high){
			int mid = low + (high - low + 1) / 2;
			if(searchSquare(mat, sum, mid, threshold)){
				low = mid;
			}else{
				high = mid - 1;
			}
		}
		return low;
	}

	private boolean searchSquare(int[][] mat, int[][] sum, int side, int threshold) {
		int m = mat.length, n = mat[0].length;
		for (int r = side; r <= m; r++) {
			for (int c = side; c <= n; c++) {
				if (sum[r][c] - sum[r - side][c] - sum[r][c - side] + sum[r - side][c - side] <= threshold)
					return true;
			}
		}
		return false;
	}
}
