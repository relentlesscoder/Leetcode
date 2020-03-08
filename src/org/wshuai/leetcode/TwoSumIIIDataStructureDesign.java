package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 01/21/2020.
 * #0170 https://leetcode.com/problems/two-sum-iii-data-structure-design/
 */
public class TwoSumIIIDataStructureDesign {
	private Map<Integer, Integer> map;
	private List<Integer> nums;

	/** Initialize your data structure here. */
	public TwoSumIIIDataStructureDesign() {
		map = new HashMap<>();
		nums = new ArrayList<>();
	}

	/** Add the number to an internal data structure.. */
	public void add(int number) {
		if(!map.containsKey(number)){
			nums.add(number);
		}
		map.put(number, map.getOrDefault(number, 0) + 1);
	}

	/** Find if there exists any pair of numbers which sum is equal to the value. */
	public boolean find(int value) {
		for(int n : nums){
			int t = value - n;
			if(n == t){
				if(map.get(n) > 1){
					return true;
				}
			}else if(map.containsKey(t)){
				return true;
			}
		}
		return false;
	}
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
