package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/5/19.
 * #560 https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {

    public int subarraySumHashMap(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        for(int i = 0; i < len; i++){
            sum += nums[i];
            if(map.containsKey(sum-k)){
                res += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) +1);
        }
        return res;
    }

    //Cumulative sum
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        int[] sum = new int[len + 1];
        sum[0] = 0;
        for(int i = 1; i <= len; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        for(int i = 0; i < len; i++){
            for(int j = i+1; j <= len; j++){
                int diff = sum[j]-sum[i];
                if(diff == k){
                    res++;
                }
            }
        }
        return res;
    }

    //DP
    public int subarraySumDP(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        int[] aux = new int[len];
        for(int i = 0; i < len; i++){
            for(int j = i; j < len; j++){
                if(i == j){
                    aux[j] = nums[j];
                }else{
                    aux[j] = aux[j-1] + nums[j];
                }
                if(aux[j] == k){
                    res++;
                }
            }
        }
        return res;
    }

}
