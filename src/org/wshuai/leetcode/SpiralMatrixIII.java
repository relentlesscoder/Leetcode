package org.wshuai.leetcode;

/**
 * Created by Wei on 9/5/19.
 * #885 https://leetcode.com/problems/spiral-matrix-iii/
 */
public class SpiralMatrixIII {
    // the walk follows the pattern below
    // east, south, west, north -> 1, 1, 2, 2 -> 3, 3, 4, 4 -> 5, 5, 6, 6 ...
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int count = 1;
        int[][] res = new int[R*C][2];

        int[] first = new int[2];
        first[0] = r0;
        first[1] = c0;
        res[count-1] = first;

        int move = 1;
        int r = r0;
        int c = c0;
        while(count < R*C){
            int east = move;
            int south = move;
            int west = move+1;
            int north = move+1;

            while(east > 0){
                c += 1;
                if(c < C && r < R && c >= 0 && r >= 0){
                    int[] curr = new int[2];
                    curr[0] = r;
                    curr[1] = c;
                    count++;
                    res[count-1] = curr;
                }
                east--;
            }

            while(south > 0){
                r += 1;
                if(c < C && r < R && c >= 0 && r >= 0){
                    int[] curr = new int[2];
                    curr[0] = r;
                    curr[1] = c;
                    count++;
                    res[count-1] = curr;
                }
                south--;
            }

            while(west > 0){
                c -= 1;
                if(c < C && r < R && c >= 0 && r >= 0){
                    int[] curr = new int[2];
                    curr[0] = r;
                    curr[1] = c;
                    count++;
                    res[count-1] = curr;
                }
                west--;
            }

            while(north > 0){
                r -= 1;
                if(c < C && r < R && c >= 0 && r >= 0){
                    int[] curr = new int[2];
                    curr[0] = r;
                    curr[1] = c;
                    count++;
                    res[count-1] = curr;
                }
                north--;
            }

            move += 2;
        }

        return res;
    }
}
