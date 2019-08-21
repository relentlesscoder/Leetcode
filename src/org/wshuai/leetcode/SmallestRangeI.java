package org.wshuai.leetcode;

/**
 * Created by Wei on 8/20/19.
 * #908 https://leetcode.com/problems/smallest-range-i/
 */
public class SmallestRangeI {
    public int smallestRangeI(int[] A, int K) {
        int max = A[0];
        int min = A[0];
        for(int i: A){
            max = i > max ? i : max;
            min = i < min ? i : min;
        }
        return Math.max(0, max - min - 2*K);
    }
}
