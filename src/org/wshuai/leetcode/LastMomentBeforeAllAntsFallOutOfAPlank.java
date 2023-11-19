package org.wshuai.leetcode;

/**
 * Created by Wei on 07/22/2020.
 * #1503 https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/
 */
public class LastMomentBeforeAllAntsFallOutOfAPlank {

    // time O(n)
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for(int l : left){
            res = Math.max(res, l);
        }
        for(int r : right){
            res = Math.max(res, n - r);
        }
        return res;
    }
}
