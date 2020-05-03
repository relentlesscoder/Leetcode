package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 05/03/2020.
 * #1431 https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 */
public class KidsWithTheGreatestNumberOfCandies {
    // time O(n)
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for(int c : candies){
            max = Math.max(max, c);
        }
        List<Boolean> res = new ArrayList<>();
        for(int c : candies){
            res.add(c + extraCandies >= max);
        }
        return res;
    }
}
