package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #733 https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {
	private int[][] aux;
	private int[][] filled;
	private int row;
	private int col;
	private int color;
	private int nColor;

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		row = image.length;
		col = image[0].length;
		color = image[sr][sc];
		nColor = newColor;
		aux = new int[2][4];
		aux[0] = new int[]{1, -1, 0, 0};
		aux[1] = new int[]{0, 0, 1, -1};
		filled = new int[row][col];
		dfs(image, sr, sc);
		return image;
	}

	private void dfs(int[][] image, int sr, int sc) {
		filled[sr][sc] = 1;
		image[sr][sc] = nColor;
		for (int i = 0; i < 4; i++) {
			int x = sr + aux[0][i];
			int y = sc + aux[1][i];
			if (x >= 0 && y >= 0 && x < row && y < col && filled[x][y] != 1 && image[x][y] == color) {
				dfs(image, x, y);
			}
		}
	}
}
