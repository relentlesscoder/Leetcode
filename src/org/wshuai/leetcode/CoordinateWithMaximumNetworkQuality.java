package org.wshuai.leetcode;

/**
 * Created by Wei on 10/19/2020.
 * #1620 https://leetcode.com/problems/coordinate-with-maximum-network-quality/
 */
public class CoordinateWithMaximumNetworkQuality {

    // time O(n*2_500)
    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] res = new int[2];
        int maxQuality = 0;
        for(int x = 0; x <= 50; x++){
            for(int y = 0; y <= 50; y++){
                int quality = 0;
                for(int[] tower : towers){
                    int xd = x - tower[0], yd = y - tower[1];
                    double distance = Math.sqrt(xd*xd + yd*yd);
                    if(distance <= (double)radius){
                        quality += (int)(tower[2] / (1 + distance));
                    }
                }
                if(quality > maxQuality){
                    maxQuality = quality;
                    res[0] = x;
                    res[1] = y;
                }
            }
        }
        return res;
    }
}
