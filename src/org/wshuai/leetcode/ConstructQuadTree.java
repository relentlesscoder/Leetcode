package org.wshuai.leetcode;

/**
 * Created by Wei on 09/13/2019.
 * #0427 https://leetcode.com/problems/construct-quad-tree/
 */
public class ConstructQuadTree {
	// time O(n^2)
	public QuadTreeNode construct(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0 || grid.length != grid[0].length){
			return null;
		}
		int n = grid.length;
		return dfs(0, 0, n - 1, n - 1, grid);
	}

	private QuadTreeNode dfs(int x1, int y1, int x2, int y2, int[][] grid){
		if(x1 == x2 && y1 == y2){
			return new QuadTreeNode(grid[x1][y1] == 1, true, null, null, null, null);
		}
		int midX = (x1 + x2) / 2, midY = (y1 + y2) / 2;
		QuadTreeNode topLeft = dfs(x1, y1, midX, midY, grid);
		QuadTreeNode topRight = dfs(x1, midY + 1, midX, y2, grid);
		QuadTreeNode bottomLeft = dfs(midX + 1, y1, x2, midY, grid);
		QuadTreeNode bottomRight = dfs(midX + 1, midY + 1, x2, y2, grid);
		if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
			&& topLeft.val == topRight.val && bottomLeft.val == bottomRight.val && topLeft.val == bottomLeft.val){
			return new QuadTreeNode(topLeft.val, true, null, null, null, null);
		}
		return new QuadTreeNode(false, false, topLeft, topRight, bottomLeft, bottomRight);
	}
}
