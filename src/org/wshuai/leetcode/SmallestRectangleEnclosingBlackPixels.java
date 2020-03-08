package org.wshuai.leetcode;

/**
 * Created by Wei on 07/20/2017.
 * #0302 https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 */
public class SmallestRectangleEnclosingBlackPixels {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(r*c)
	public int minArea(char[][] image, int x, int y) {
		if(image == null || image.length == 0 || image[0].length == 0){
			return 0;
		}
		int[] coordinates = new int[]{x, x, y, y};
		dfs(x, y, image.length, image[0].length, image, coordinates);
		return (coordinates[1] - coordinates[0] + 1) * (coordinates[3] - coordinates[2] + 1);
	}

	private void dfs(int i, int j, int r, int c, char[][] image, int[] coordinates){
		image[i][j] = '0';
		for(int k = 0; k < 4; k++){
			int x = i + dirs[k];
			int y = j + dirs[k + 1];
			if(x >= 0 && x < r && y >= 0 && y < c && image[x][y] == '1'){
				coordinates[0] = Math.min(coordinates[0], x);
				coordinates[1] = Math.max(coordinates[1], x);
				coordinates[2] = Math.min(coordinates[2], y);
				coordinates[3] = Math.max(coordinates[3], y);
				dfs(x, y, r, c, image, coordinates);
			}
		}
	}
}
