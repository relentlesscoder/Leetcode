package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/24/2020.
 * #1682 https://leetcode.com/problems/longest-palindromic-subsequence-ii/
 */
public class MaxNumberOfKSumPairs {

    // time O(n*log(n))
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0, i = 0, j = nums.length - 1;
        while(i < j){
            int sum = nums[i] + nums[j];
            if(sum == k){
                res++;
                i++;
                j--;
            }else if(sum > k){
                j--;
            }else{
                i++;
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int maxOperationsHashMap(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int diff = k - nums[i], count = map.getOrDefault(diff, 0);
            if(count > 0){
                res++;
                map.put(diff, --count);
            }else{
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        return res;
    }
}
