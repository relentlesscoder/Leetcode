package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 03/22/2020.
 * #1389 https://leetcode.com/problems/create-target-array-in-the-given-order/
 */
public class CreateTargetArrayInTheGivenOrder {

	// time O(n^2)
	public int[] createTargetArray(int[] nums, int[] index) {
		int n = nums.length;
		int[] res = new int[n];
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < n; i++){
			temp.add(index[i], nums[i]);
		}
		for(int i = 0; i < n; i++){
			res[i] = temp.get(i);
		}
		return res;
	}

	// time O(n^2)
	public int[] createTargetArrayUpdateIndex(int[] nums, int[] index) {
		int n = nums.length;
		int[] res = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				if(index[j] >= index[i]){
					index[j]++;
				}
			}
		}
		for(int i = 0; i < n; i++){
			res[index[i]] = nums[i];
		}
		return res;
	}
}
