package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/21/2021.
 * #1852 https://leetcode.com/problems/distinct-numbers-in-each-subarray/
 */
public class DistinctNumbersInEachSubarray {

    // time O(n), space O(k)
    public int[] distinctNumbers(int[] nums, int k) {
        Map<Integer, Integer> valueCount = new HashMap<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for(int i = 0; i < k; i++){
            valueCount.put(nums[i], valueCount.getOrDefault(nums[i], 0) + 1);
        }
        res[0] = valueCount.size();
        for(int i = k, j = 1; i < n; i++){
            valueCount.put(nums[i], valueCount.getOrDefault(nums[i], 0) + 1);
            int head = nums[i - k], count = valueCount.get(head) - 1;
            if(count == 0){
                valueCount.remove(head);
            }else{
                valueCount.put(head, count);
            }
            res[j++] = valueCount.size();
        }
        return res;
    }
}
