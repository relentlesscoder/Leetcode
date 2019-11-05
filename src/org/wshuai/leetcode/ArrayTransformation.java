package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/5/19.
 * #1243 https://leetcode.com/problems/array-transformation/
 */
public class ArrayTransformation {
	public List<Integer> transformArray(int[] arr) {
		int N = arr.length;
		int[] ans = new int[N];
		while(!equalsArr(ans, arr)){
			copyArr(ans, arr);
			for(int i = 1; i <= N - 2; i++){
				if(ans[i - 1] < ans[i] && ans[i + 1] < ans[i]){
					arr[i]--;
				}else if(ans[i - 1] > ans[i] && ans[i + 1] > ans[i]){
					arr[i]++;
				}
			}
		}
		List<Integer> res = new ArrayList<>();
		for(int a : arr){
			res.add(a);
		}
		return res;
	}

	private boolean equalsArr(int[] a, int[] b){
		for(int i = 0; i < a.length; i++){
			if(a[i] != b[i]){
				return false;
			}
		}
		return true;
	}

	private void copyArr(int[] to, int[] from){
		for(int i = 0; i < from.length; i++){
			to[i] = from[i];
		}
	}
}
