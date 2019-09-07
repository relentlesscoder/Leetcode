package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RottingOranges;

public class RottingOrangesTest {
    @Test
    public void testcase(){
        RottingOranges ro = new RottingOranges();
        int[][] grid = new int[3][3];
        grid[0] = new int[]{2,1,1};
        grid[1] = new int[]{1,1,0};
        grid[2] = new int[]{0,1,1};
        int min = ro.orangesRotting(grid);
    }
}
