package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2020.
 * #1637 https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/
 */
public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {

    // time O(n*log(n))
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int max = 0, prev = points[0][0];
        for(int i = 1; i < points.length; i++){
            if(points[i][0] != prev){
                max = Math.max(max, points[i][0] - prev);
                prev = points[i][0];
            }
        }
        return max;
    }
}
