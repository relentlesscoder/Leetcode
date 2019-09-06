package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #750 https://leetcode.com/problems/number-of-corner-rectangles/
 */
public class NumberOfCornerRectangles {
    public int countCornerRectangles(int[][] grid) {
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row-1; i++){
            for(int j = i+1; j < row; j++){
                int c = 0;
                for(int k = 0; k < col; k++){
                    // find two cells on the same column both equal to 1 between the two rows
                    if(grid[i][k] == 1 && grid[j][k] == 1){
                        c++;
                    }
                }
                while(c > 0){
                    count += (c - 1);
                    c--;
                }
            }
        }
        return count;
    }
}
