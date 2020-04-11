package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/11/2019.
 * #0658 https://leetcode.com/problems/find-k-closest-elements/
 */
public class FindKClosestElements {

	// time O(log(n) + k)
	// https://leetcode.com/problems/find-k-closest-elements/discuss/106426/JavaC%2B%2BPython-Binary-Search-O(log(N-K)-%2B-K)
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int left = 0, right = arr.length - k;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(x - arr[mid] > arr[mid + k] - x){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		List<Integer> res = new ArrayList<>();
		for(int i = left; i < left + k; i++){
			res.add(arr[i]);
		}
		return res;
	}
}
