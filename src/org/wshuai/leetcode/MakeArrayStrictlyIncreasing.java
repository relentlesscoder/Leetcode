package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/10/2019.
 * #1187 https://leetcode.com/problems/make-array-strictly-increasing/
 */
public class MakeArrayStrictlyIncreasing {
	public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		Arrays.sort(arr2);
		Map<Integer, Integer> dp = new HashMap<>();
		dp.put(-1, 0);
		for(int a1 : arr1){
			Map<Integer, Integer> temp = new HashMap<>();
			for(Map.Entry<Integer, Integer> entry : dp.entrySet()){
				int key = entry.getKey();
				int val = entry.getValue();
				if(a1 > key){
					int lastCount = temp.containsKey(a1) ? temp.get(a1) : Integer.MAX_VALUE;
					temp.put(a1, Math.min(lastCount, val));
				}
				int i = binarySearch(arr2, key);
				if(i != arr2.length){
					int lastCount = temp.containsKey(arr2[i]) ? temp.get(arr2[i]) : Integer.MAX_VALUE;
					temp.put(arr2[i], Math.min(lastCount, val + 1));
				}
			}
			dp = temp;
		}
		int res = Integer.MAX_VALUE;
		if(dp.size() == 0){
			res = -1;
		}else{
			for(Map.Entry<Integer, Integer> entry : dp.entrySet()){
				res = Math.min(res, entry.getValue());
			}
		}
		return res;
	}

	private int binarySearch(int[] arr, int t){
		int left = 0;
		int right = arr.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] <= t){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
