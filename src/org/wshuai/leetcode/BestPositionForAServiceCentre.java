package org.wshuai.leetcode;

/**
 * Created by Wei on 08/04/2020.
 * #1515 https://leetcode.com/problems/best-position-for-a-service-centre/
 */
public class BestPositionForAServiceCentre {

    private static final double LOWER_LIMIT = 0.0000001;

    private static final int[][] dirs = new int[][]{
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    // time O(n*log(d))
    // https://www.geeksforgeeks.org/geometric-median/
    public double getMinDistSum(int[][] positions) {
        int n = positions.length;
        double x = 0.0, y = 0.0;
        for(int[] p : positions){
            x += p[0];
            y += p[1];
        }
        x /= n;
        y /= n;
        double minDist = distSum(x, y, positions, n);
        double testDistance = 100.0;
        while(testDistance >= LOWER_LIMIT){
            boolean found = false;
            for(int i = 0; i < 4; i++){
                double nx = x + testDistance * dirs[i][0], ny = y + testDistance * dirs[i][1];
                double newDist = distSum(nx, ny, positions, n);
                // smaller distance found
                if(newDist < minDist){
                    minDist = newDist;
                    x = nx;
                    y = ny;
                    found = true;
                    break;
                }
            }
            testDistance /= found ? 1 : 2;
        }
        return minDist;
    }

    private double distSum(double x, double y, int[][] positions, int n){
        double res = 0.0;
        for(int i = 0; i < n; i++){
            double distX = Math.abs(x - positions[i][0]);
            double distY = Math.abs(y - positions[i][1]);
            res += Math.sqrt(distX * distX + distY * distY);
        }
        return res;
    }
}
