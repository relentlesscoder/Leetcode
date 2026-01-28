package org.wshuai.leetcode;

/**
 * Created by Wei on 09/13/2019.
 * #0427 https://leetcode.com/problems/construct-quad-tree/
 */
public class ConstructQuadTree {

    // time O(n^2 * log(n)), space O(n^2)
    public Node construct(int[][] grid) {
        int n = grid.length;
        return dfs(grid, 0, n - 1, 0, n - 1);
    }

    private Node dfs(int[][] grid, int r1, int r2, int c1, int c2) {
		// 只有一个点
        if (r1 == r2 && c1 == c2) {
            return new Node(grid[r1][c1] == 1, true);
        }
		// 平分为上下左右四个部分递归
        Node topLeft = dfs(grid, r1, (r1 + r2) / 2, c1, (c1 + c2) / 2);
        Node topRight = dfs(grid, r1, (r1 + r2) / 2, (c1 + c2) / 2 + 1, c2);
        Node bottomLeft = dfs(grid, (r1 + r2) / 2 + 1, r2, c1, (c1 + c2) / 2);
        Node bottomRight = dfs(grid, (r1 + r2) / 2 + 1, r2, (c1 + c2) / 2 + 1, c2);
		// 如果全部为叶节点且值相同，合并为一个叶子节点
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topLeft.val == bottomLeft.val
                && topLeft.val == bottomRight.val) {
            return new Node(grid[r1][c1] == 1, true);
        } else { // 否则返回一个有四个子节点的节点
            return new Node(grid[r1][c1] == 1, false,
					topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    /**
     * Definition for a QuadTree node.
     */
    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf,
					Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
