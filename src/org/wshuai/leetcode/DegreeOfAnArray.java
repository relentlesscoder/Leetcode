package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/20/19.
 * #697 https://leetcode.com/problems/degree-of-an-array/
 */
public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        int degree = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            List<Integer> lst = map.getOrDefault(nums[i], null);
            if(lst == null){
                lst = new ArrayList<>();
                lst.add(i);
                lst.add(i);
                lst.add(0);
                map.put(nums[i], lst);
            }
            int fre = lst.get(2) + 1;
            degree = fre > degree ? fre : degree;
            lst.set(1, i);
            lst.set(2, fre);
        }
        int min = nums.length;
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            List<Integer> lst = entry.getValue();
            if(lst.get(2) != degree){
                continue;
            }
            int len = lst.get(1) - lst.get(0) + 1;
            min = len < min ? len : min;
        }
        return min;
    }
}
