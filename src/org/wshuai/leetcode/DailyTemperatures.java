package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/6/19.
 * #739 https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        int[] next = new int[101];
        Arrays.fill(next, -1);
        for(int i = T.length-1; i >= 0; i--){
            int temp = T[i];
            int min = 50000;
            // find the min index that has value greater than the current temp
            for(int j = temp + 1; j < 101; j++){
                if(next[j] != -1){
                    min = Math.min(min, next[j]);
                }
            }
            res[i] = min == 50000 ? 0 : min - i;
            next[temp] = i;
        }
        return res;
    }
}
