package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/16/2020.
 * #1553 https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class MinimumNumberOfDaysToEatNOranges {

    public int minDays(int n) {
        return dfs(n, new HashMap<Integer, Integer>());
    }

    private int dfs(int n, Map<Integer, Integer> dp){
        if(n <= 1){
            return n;
        }
        if(!dp.containsKey(n)){
            dp.put(n, 1 + Math.min(n % 2 + dfs(n/2, dp), n % 3 + dfs(n/3, dp)));
        }
        return dp.get(n);
    }
}
