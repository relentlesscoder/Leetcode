package org.wshuai.leetcode;

/**
 * Created by Wei on 8/21/19.
 * #849 https://leetcode.com/problems/maximize-distance-to-closest-person/
 */
public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int len = seats.length;
        int prev = -1;
        int future = 0;
        int ans = 0;

        for(int i = 0; i < len; i++){
            if(seats[i] == 1){
                prev = i;
            }else{
                while(future < len && seats[future] == 0 || future < i){
                    future++;
                }

                // set distance to the array length to exclude the cases that i is either at the first or the last
                int left = prev == -1 ? len : i - prev;
                int right = future == len ? len : future - i;
                ans = Math.max(ans, Math.min(left, right));
            }
        }

        return ans;
    }
}
