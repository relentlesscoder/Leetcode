package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/08/2019.
 * #1213 https://leetcode.com/problems/intersection-of-three-sorted-arrays/
 */
public class IntersectionOfThreeSortedArrays {

	// time O(n)
	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
		List<Integer> res = new ArrayList<>();
		for(int i = 0, j = 0, k = 0; i < arr1.length && j < arr2.length && k < arr3.length; ){
			if(arr1[i] == arr2[j] && arr1[i] == arr3[k]){
				res.add(arr1[i]);
				i++;
				j++;
				k++;
				continue;
			}
			int max = Math.max(Math.max(arr1[i], arr2[j]), arr3[k]);
			while(i < arr1.length && arr1[i] < max){
				i++;
			}
			while(j < arr2.length && arr2[j] < max){
				j++;
			}
			while(k < arr3.length && arr3[k] < max){
				k++;
			}
		}
		return res;
	}
}
