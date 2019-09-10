package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 9/9/19.
 * #694 https://leetcode.com/problems/number-of-distinct-islands/
 */
public class NumberOfDistinctIslands {
    int[][] grid;
    boolean[][] seen;
    Set<Integer> shape;

    private void explore(int r, int c, int r0, int c0){
        if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]){
            seen[r][c] = true;
            shape.add((r-r0)*2*grid[0].length + (c-c0));
            explore(r+1, c, r0, c0);
            explore(r-1, c, r0, c0);
            explore(r, c+1, r0, c0);
            explore(r, c-1, r0, c0);
        }
    }

    // https://stackoverflow.com/questions/35702631/why-does-the-hashcode-of-an-arraylist-change-every-time-you-add-a-new-element?rq=1
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<HashSet<Integer>>();

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                shape = new HashSet<Integer>();
                explore(i, j, i, j);
                if(!shape.isEmpty()){
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }
}
