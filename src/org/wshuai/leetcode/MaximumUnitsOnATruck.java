package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/13/2021.
 * #1710 https://leetcode.com/problems/maximum-units-on-a-truck/
 */
public class MaximumUnitsOnATruck {

    // time O(n*log(n))
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int units = 0;
        for(int i = 0; i < boxTypes.length && truckSize > 0; i++){
            int boxes = Math.min(truckSize, boxTypes[i][0]);
            units += boxes * boxTypes[i][1];
            truckSize -= boxes;
        }
        return units;
    }
}
