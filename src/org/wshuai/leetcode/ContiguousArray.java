package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 2/27/17.
 * #525 https://leetcode.com/problems/contiguous-array/
 */
public class ContiguousArray {
	public int findMaxLength(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 0) {
				nums[i] = -1;
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int max = 0;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			//in order to get longest sub-array,
			//don't update the map
			if (map.containsKey(sum)) {
				int idx = map.get(sum);
				max = Math.max(max, i - idx);
			} else {
				map.put(sum, i);
			}
		}
		return max;
	}
}
