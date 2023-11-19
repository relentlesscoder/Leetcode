package org.wshuai.leetcode;

/**
 * Created by Wei on 02/02/2021.
 * #1732 https://leetcode.com/problems/find-the-highest-altitude/
 */
public class FindTheHighestAltitude {

    // time O(n)
    public int largestAltitude(int[] gain) {
        int max = 0, cur = 0;
        for(int g : gain){
            cur += g;
            max = Math.max(max, cur);
        }
        return max;
    }
}
