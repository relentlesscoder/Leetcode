package org.wshuai.leetcode;

/**
 * Created by Wei on 9/13/2019.
 * #427 https://leetcode.com/problems/construct-quad-tree/
 */
public class ConstructQuadTree {
    public QuadTreeNode construct(int[][] grid) {
        int N = grid.length;
        return dfs(grid, 0, 0, N-1, N-1);
    }

    public QuadTreeNode dfs(int[][] grid, int x1, int y1, int x2, int y2){
        if(x1 > x2){
            return null;
        }
        boolean isLeaf = true;
        int val = grid[x1][y1];
        for(int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                if(grid[i][j] != val){
                    isLeaf = false;
                    break;
                }
            }
        }
        if(isLeaf){
            return new QuadTreeNode(val==1, true, null, null, null, null);
        }
        else{
            int rMid = (x1+x2)/2;
            int cMid = (y1+y2)/2;
            QuadTreeNode topLeft = dfs(grid, x1, y1, rMid, cMid);
            QuadTreeNode topRight = dfs(grid, x1, cMid+1, rMid, y2);
            QuadTreeNode bottomLeft = dfs(grid, rMid+1, y1, x2, cMid);
            QuadTreeNode bottomRight = dfs(grid, rMid+1, cMid+1, x2, y2);
            return new QuadTreeNode(false, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }
}
