package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/12/2020.
 * #1346 https://leetcode.com/problems/check-if-n-and-its-double-exist/
 */
public class CheckIfNAndItsDoubleExist {
	// time O(n), space O(n)
	public boolean checkIfExist(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = arr.length;
		for(int i = 0; i < n; i++){
			// two cases:
			// [14, 5, 7, 8]
			// [5, 6, 10, 7]
			if(map.containsKey(arr[i] << 1)
					|| (arr[i] % 2 == 0 && map.containsKey(arr[i] >> 1))){
				return true;
			}
			map.put(arr[i], i);
		}
		return false;
	}
}
