package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0733 https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {

	private static int[] DIRECTIONS = new int[]{0, 1, 0, -1, 0};

	// time O(m*n)
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if(image[sr][sc] == newColor){
			return image;
		}
		dfs(sr, sc, image.length, image[0].length, image[sr][sc], newColor, image);
		return image;
	}

	private void dfs(int i, int j, int r, int c, int from, int to, int[][] image){
		image[i][j] = to;
		for(int k = 0; k < 4; k++){
			int x = i + DIRECTIONS[k], y = j + DIRECTIONS[k + 1];
			if(x >= 0 && x < r && y >= 0 && y < c && image[x][y] == from){
				dfs(x, y, r, c, from, to, image);
			}
		}
	}
}
