package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0733 https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {

    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// time O(m * n), space O(m * n)
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int pre, int color) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length
                || image[r][c] != pre || image[r][c] == color) {
            return;
        }
        image[r][c] = color;
        for (int d = 0; d < 4; d++) {
            int x = r + DIRS[d], y = c + DIRS[d + 1];
            dfs(image, x, y, pre, color);
        }
    }
}
