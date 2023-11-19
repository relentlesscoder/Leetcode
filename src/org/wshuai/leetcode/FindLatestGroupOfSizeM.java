package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 09/01/2020.
 * #1562 https://leetcode.com/problems/find-latest-group-of-size-m/
 */
public class FindLatestGroupOfSizeM {

	// time O(n), space O(n)
	public int findLatestStep(int[] arr, int m) {
		int res = -1, n = arr.length;
		if(m == n){
			return n;
		}
		int[] length = new int[n + 2];
		for(int i = 0; i < n; i++){
			int cur = arr[i], left = length[cur - 1], right = length[cur + 1];
			length[cur - left] = length[cur + right] = left + right + 1;
			if(left == m || right == m){
				res = i;
			}
		}
		return res;
	}

	// time O(n*log(n)), space O(n)
	public int findLatestStepTreeMap(int[] arr, int m) {
		int n = arr.length;
		if(m == n){
			return n;
		}
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(1, n);
		for(int i = n - 1; i >= 0; i--){
			int cur = arr[i];
			Integer floor = map.floorKey(cur);
			Integer ceil = map.get(floor);
			if(cur - floor == m || ceil - cur == m){
				return i;
			}else{
				map.put(floor, cur - 1);
				map.put(cur + 1, ceil);
			}
		}
		return -1;
	}
}
