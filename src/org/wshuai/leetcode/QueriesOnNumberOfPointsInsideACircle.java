package org.wshuai.leetcode;

/**
 * Created by Wei on 05/19/2021.
 * #1828 https://leetcode.com/problems/queries-on-number-of-points-inside-a-circle/
 */
public class QueriesOnNumberOfPointsInsideACircle {

    // time O(m*n)
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int count = 0;
            int[] q = queries[i];
            for(int[] p : points){
                if((q[0] - p[0]) * (q[0] - p[0]) + (q[1] - p[1]) * (q[1] - p[1]) <= q[2]*q[2]){
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }
}
