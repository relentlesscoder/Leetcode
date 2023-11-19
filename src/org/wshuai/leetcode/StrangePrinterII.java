package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/04/2020.
 * #1591 https://leetcode.com/problems/strange-printer-ii/
 */
public class StrangePrinterII {

    // time O(c^2*m*n), space O(4*c)
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length, n = targetGrid[0].length;
        int[][] rectangles = new int[61][4];
        for(int i = 1; i < 61; i++){
            rectangles[i] = new int[]{61, 61, -1, -1};
        }
        Set<Integer> colors = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int c = targetGrid[i][j];
                colors.add(c);
                rectangles[c][0] = Math.min(rectangles[c][0], i);
                rectangles[c][1] = Math.min(rectangles[c][1], j);
                rectangles[c][2] = Math.max(rectangles[c][2], i);
                rectangles[c][3] = Math.max(rectangles[c][3], j);
            }
        }
        while(colors.size() > 0){
            Set<Integer> next = new HashSet<>();
            for(int c : colors){
                if(!dfs(targetGrid, rectangles, c)){
                    next.add(c);
                }
            }
            if(colors.size() == next.size()){
                return false;
            }
            colors = next;
        }
        return true;
    }

    private boolean dfs(int[][] targetGrid, int[][] rectangles, int color){
        for(int i = rectangles[color][0]; i <= rectangles[color][2]; i++){
            for(int j = rectangles[color][1]; j <= rectangles[color][3]; j++){
                if(targetGrid[i][j] > 0 && targetGrid[i][j] != color){
                    return false;
                }
            }
        }
        for(int i = rectangles[color][0]; i <= rectangles[color][2]; i++){
            for(int j = rectangles[color][1]; j <= rectangles[color][3]; j++){
                targetGrid[i][j] = 0;
            }
        }
        return true;
    }
}
