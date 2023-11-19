package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by Wei on 05/02/2020.
 * #1429 https://leetcode.com/problems/first-unique-number/
 */
public class FirstUniqueNumber {

	private LinkedHashSet<Integer> unique;
	private Map<Integer, Integer> count;

	public FirstUniqueNumber(int[] nums) {
		unique = new LinkedHashSet<>();
		count = new HashMap<>();
		for(int num : nums){
			count.put(num, count.getOrDefault(num, 0) + 1);
			if(count.get(num) == 1){
				unique.add(num);
			}else{
				unique.remove(num);
			}
		}
	}

	public int showFirstUnique() {
		return unique.isEmpty() ? -1 : unique.iterator().next();
	}

	public void add(int value) {
		count.put(value, count.getOrDefault(value, 0) + 1);
		if(count.get(value) == 1){
			unique.add(value);
		}else{
			unique.remove(value);
		}
	}
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
