package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/20/2021.
 * #1726 https://leetcode.com/problems/tuple-with-same-product/
 */
public class TupleWithSameProduct {

    // time O(n^2), space O(n^2)
    public int tupleSameProduct(int[] nums) {
        int res = 0, n = nums.length;
        if(n < 4){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int prod = nums[i] * nums[j], cur = map.getOrDefault(prod, 0);
                res += cur << 3; // cur*8
                map.put(prod, cur + 1);
            }
        }
        return res;
    }
}
