package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/10/2019.
 * #0666 https://leetcode.com/problems/path-sum-iv/
 */
public class PathSumIV {
	// time O(n), space O(n)
	public int pathSum(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0;
		Map<Integer, int[]> map = new HashMap<>();
		map.put(0, new int[]{nums[0] % 10, 0});
		for (int i = 1; i < nums.length; i++) {
			int depth = nums[i] / 100 - 1;
			int index = (nums[i] / 10) % 10 - 1;
			int val = nums[i] % 10;
			int parent = (depth - 1) * 10 + (index >> 1);
			map.put(depth * 10 + index, new int[]{val + map.get(parent)[0], 0});
			map.get(parent)[1] = 1;
		}
		for (int[] vals : map.values()) {
			res += vals[1] == 0 ? vals[0] : 0;
		}
		return res;
	}
}
