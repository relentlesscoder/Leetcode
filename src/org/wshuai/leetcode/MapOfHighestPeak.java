package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 03/02/2021.
 * #1765 https://leetcode.com/problems/map-of-highest-peak/
 */
public class MapOfHighestPeak {

    private static final int[] DIRECTIONS = new int[]{ 0, -1, 0, 1, 0 };

    // time O(m*n)
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length, height = 0;
        int[][] res = new int[m][n];
        LinkedList<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(isWater[i][j] == 1){
                    queue.offerLast(new int[]{i, j});
                    isWater[i][j] = -1; // use isWater to record visited status
                }
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] cur = queue.pollFirst();
                res[cur[0]][cur[1]] = height;
                for(int d = 0; d < 4; d++){
                    int x = cur[0] + DIRECTIONS[d], y = cur[1] + DIRECTIONS[d + 1];
                    if(x < 0 || x >= m || y < 0 || y >= n || isWater[x][y] == -1){
                        continue;
                    }
                    isWater[x][y] = -1;
                    queue.offerLast(new int[]{x, y});
                }
            }
            height++;
        }
        return res;
    }
}
