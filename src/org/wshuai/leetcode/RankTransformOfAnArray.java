package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Created by Wei on 01/26/2020.
 * #1331 https://leetcode.com/problems/rank-transform-of-an-array/
 */
public class RankTransformOfAnArray {
	// time O(n), space O(n)
	public int[] arrayRankTransform(int[] arr) {
		if(arr == null || arr.length == 0){
			return new int[0];
		}
		int n = arr.length;
		int[] copy = Arrays.copyOfRange(arr, 0, arr.length);;
		Arrays.sort(copy);
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		for(int a : copy){
			map.putIfAbsent(a, map.size() + 1);
		}
		int[] res = new int[n];
		for(int i = 0, j = 0; i < n; i++){
			res[j++] = map.get(arr[i]);
		}
		return res;
	}
}
