package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/19.
 * #845 https://leetcode.com/problems/longest-mountain-in-array/
 */
public class LongestMountainInArray {
	public int longestMountain(int[] A) {
		int res = 0;
		int len = A.length;
		if(len < 3){
			return 0;
		}
		int i = 0;
		int j = 0;
		int k = 0;
		while(j < len){
			// find the next (possible) starting point of a mountain
			if(j < len - 1 && A[j + 1] > A[j]){
				i = j;
				while(j < len - 1 && A[j + 1] > A[j]){
					j++;
				}
				// record the (possible) mountain peak
				k = j;
				while(j < len - 1 && A[j + 1] < A[j]){
					j++;
				}
				// check if the (possible) mountain is valid
				if(i != k && k != j){
					res = Math.max(res, j - i + 1);
				}
			}else{
				j++;
			}
		}
		return res;
	}
}
