package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 12/26/2019.
 * #1224 https://leetcode.com/problems/maximum-equal-frequency/
 */
public class MaximumEqualFrequency {

	public int maxEqualFreq(int[] nums) {
		int res = 0;
		Map<Integer, Integer> valCount = new HashMap<>();
		Map<Integer, Set<Integer>> countVal = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			int cnt = valCount.getOrDefault(nums[i], 0);
			if(cnt == 0){
				valCount.put(nums[i], 1);
				countVal.putIfAbsent(1, new HashSet<>());
				countVal.get(1).add(nums[i]);
			}else{
				valCount.put(nums[i], cnt + 1);
				countVal.putIfAbsent(cnt + 1, new HashSet<>());
				countVal.get(cnt + 1).add(nums[i]);
				countVal.get(cnt).remove(nums[i]);
				if(countVal.get(cnt).size() == 0){
					countVal.remove(cnt);
				}
			}
			/*
			case1: 4,4,4,4,4
			case2: 4,4,4,3,3,3,3,5,5,5
			case3: 4,4,4,3,3,3,5
			case4: 1,2,3,4,5,6,7,8,9
			*/
			if(valCount.size() == 1){
				res = Math.max(res, i + 1);
			}else if(countVal.size() == 1 && countVal.containsKey(1)){
				res = Math.max(res, i + 1);
			}else if(countVal.size() == 2){
				int firstKey = -1;
				int firstCount = -1;
				int secondKey = -1;
				int secondCount = -1;
				for(Map.Entry<Integer, Set<Integer>> entry : countVal.entrySet()){
					if(firstKey == -1){
						firstKey = entry.getKey();
						firstCount = entry.getValue().size();
					}else{
						secondKey = entry.getKey();
						secondCount = entry.getValue().size();
						break;
					}
				}
				if((firstKey == 1 && firstCount == 1) || (secondKey == 1 && secondCount == 1)){
					res = Math.max(res, i + 1);
				}else if(Math.abs(firstKey - secondKey) == 1
						&& ((firstKey > secondKey && firstCount == 1)
						||  (secondKey > firstKey && secondCount == 1))){
					res = Math.max(res, i + 1);
				}
			}
		}
		return res;
	}
}
