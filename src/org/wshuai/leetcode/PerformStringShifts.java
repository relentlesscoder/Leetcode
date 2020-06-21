package org.wshuai.leetcode;

/**
 * Created by Wei on 05/01/2020.
 * #1427 https://leetcode.com/problems/perform-string-shifts/
 */
public class PerformStringShifts {
    // time O(n)
    public String stringShift(String s, int[][] shift) {
        // two observation:
        // 1. the order of shift does not matter
        // 2. left and right shift offset each other
        int n = s.length(), move = 0, start = -1;
        // calculate the final move
        for(int[] sh : shift){
            move += sh[0] == 0 ? -sh[1] : sh[1];
        }
        if(move == 0){
            return s;
        }else if(move < 0){
            // left shift
            start = (-move) % n;
        }else{
            // right shift
            start = n - (move % n);
        }
        return s.substring(start) + s.substring(0, start);
    }
}
