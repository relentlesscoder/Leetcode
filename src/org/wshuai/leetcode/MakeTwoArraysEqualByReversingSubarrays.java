package org.wshuai.leetcode;

/**
 * Created by Wei on 05/31/2020.
 * #1460 https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 */
public class MakeTwoArraysEqualByReversingSubarrays {

	// time O(n)
	public boolean canBeEqual(int[] target, int[] arr) {
		int[] targetCount = new int[1_001], arrCount = new int[1_001];
		for(int i = 0; i < arr.length; i++){
			targetCount[target[i]]++;
			arrCount[arr[i]]++;
		}
		for(int i = 1; i <= 1_000; i++){
			if(targetCount[i] != arrCount[i]){
				return false;
			}
		}
		return true;
	}
}
