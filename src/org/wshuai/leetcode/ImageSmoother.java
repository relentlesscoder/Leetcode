package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #661 https://leetcode.com/problems/image-smoother/
 */
public class ImageSmoother {
	public int[][] imageSmoother(int[][] M) {
		int row = M.length;
		int col = M[0].length;
		int[][] res = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int count = 0;
				int sum = 0;
				// check the range
				int xs = i >= 1 ? i - 1 : i;
				int xe = i < row - 1 ? i + 1 : i;
				int ys = j >= 1 ? j - 1 : j;
				int ye = j < col - 1 ? j + 1 : j;
				for (int x = xs; x <= xe; x++) {
					for (int y = ys; y <= ye; y++) {
						sum += M[x][y];
						count++;
					}
				}
				res[i][j] = (int) Math.floor(sum * 1.0 / count);
			}
		}
		return res;
	}
}
