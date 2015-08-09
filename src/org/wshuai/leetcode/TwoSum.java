package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/9/15.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target){
        int[] result = new int[2];
        if(nums.length > 1){
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i = 0; i < nums.length; i++){
                map.put(nums[i], i);
            }
            for(int i = 0; i < nums.length; i++){
                int diff = target - nums[i];
                Integer index = map.get(diff);
                if(index != null && (index != i)){
                    if(index < i){
                        result[0] = index + 1;
                        result[1] = i + 1;
                    }
                    else{
                        result[0] = i + 1;
                        result[1] = index + 1;
                    }
                    break;
                }
                else{
                    continue;
                }
            }
        }
        return result;
    }
}

