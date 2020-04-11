package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/22/2020.
 * #1387 https://leetcode.com/problems/sort-integers-by-the-power-value/
 */
public class SortIntegersByThePowerValue {
	// time O(n*log(n)), space O(n)
	public int getKth(int lo, int hi, int k) {
		int[][] nums = new int[hi - lo + 1][2];
		for(int i = 0; i < nums.length; i++){
			nums[i][0] = lo + i;
		}
		getPower(nums);
		Arrays.sort(nums, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
		return nums[k - 1][0];
	}

	private void getPower(int[][] nums){
		Map<Integer, Integer> map = new HashMap<>();
		map.put(2, 1);
		map.put(4, 2);
		map.put(8, 3);
		map.put(16, 4);
		for(int i = 0; i < nums.length; i++){
			nums[i][1] = findPower(nums[i][0], map);
		}
	}

	private int findPower(int num, Map<Integer, Integer> map){
		if(map.containsKey(num)){
			return map.get(num);
		}
		int res = 0;
		if(num % 2 == 0){
			res = findPower(num / 2, map) + 1;
		}else{
			res = findPower(3 * num + 1, map) + 1;
		}
		map.put(num, res);
		return res;
	}
}
