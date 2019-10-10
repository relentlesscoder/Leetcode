package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/10/2019.
 * #666 https://leetcode.com/problems/path-sum-iv/
 */
public class PathSumIV {
	public int pathSum(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0;
		Map<Integer, int[]> map = new HashMap<>();
		map.put(11, new int[]{nums[0] % 10, 0});
		for(int i = 1; i < nums.length; i++){
			int x = nums[i] / 100;
			int y = nums[i] / 10 - x * 10;
			int z = nums[i] % 10;
			// get path sum of the parent node
			int key = (x - 1) * 10 + (y % 2 == 0 ? y : y + 1) / 2;
			int sum = map.get(key)[0] + z;
			map.put(x * 10 + y, new int[]{sum, 0});
			// mark the parent node as internal nodes
			map.get(key)[1] = 1;
		}
		for(int[] v: map.values()){
			// only count in leaf nodes
			res += v[1] == 0 ? v[0] : 0;
		}
		return res;
	}
}
