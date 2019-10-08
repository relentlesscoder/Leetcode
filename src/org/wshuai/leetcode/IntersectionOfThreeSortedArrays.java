package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/8/2019.
 * #1213 https://leetcode.com/problems/intersection-of-three-sorted-arrays/
 */
public class IntersectionOfThreeSortedArrays {
	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
		List<Integer> res = new ArrayList<>();
		int i1 = 0, i2 = 0, i3 = 0;
		while(i1 < arr1.length && i2 < arr2.length && i3 < arr3.length){
			if(arr1[i1] == arr2[i2] && arr1[i1] == arr3[i3]){
				res.add(arr1[i1]);
				i1++;
				i2++;
				i3++;
				continue;
			}
			int max = Math.max(Math.max(arr1[i1], arr2[i2]), arr3[i3]);
			while(i1 < arr1.length && arr1[i1] < max){
				i1++;
			}
			while(i2 < arr2.length && arr2[i2] < max){
				i2++;
			}
			while(i3 < arr3.length && arr3[i3] < max){
				i3++;
			}
		}
		return res;
	}
}
