package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 09/30/2019.
 * #0969 https://leetcode.com/problems/pancake-sorting/
 */
public class PancakeSorting {

	// time O(n^2)
	public List<Integer> pancakeSort(int[] arr) {
		List<Integer> res = new ArrayList<>();
		for(int k = arr.length, i; k > 0; k--){
			// reverse [1...i] put the current max k to the front
			for(i = 0; arr[i] != k; i++);
			reverse(arr, i + 1);
			res.add(i + 1);
			// reverse [1...k] put k to the end
			reverse(arr, k);
			res.add(k);
		}
		return res;
	}

	private void reverse(int[] arr, int k){
		for(int i = 0, j = k - 1; i < j; i++, j--){
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
}
