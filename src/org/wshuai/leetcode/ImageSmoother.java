package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0661 https://leetcode.com/problems/image-smoother/
 */
public class ImageSmoother {
	// time O(r*c)
	public int[][] imageSmoother(int[][] M) {
		int r = M.length, c = M[0].length;
		int[][] res = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				int sum = 0, count = 0;
				int xs = i > 0 ? i - 1 : i;
				int xe = i < r - 1 ? i + 1 : i;
				int ys = j > 0 ? j - 1 : j;
				int ye = j < c - 1 ? j + 1 : j;
				for (int s = xs; s <= xe; s++) {
					for (int e = ys; e <= ye; e++) {
						sum += M[s][e];
						count++;
					}
				}
				res[i][j] = sum / count;
			}
		}
		return res;
	}
}
