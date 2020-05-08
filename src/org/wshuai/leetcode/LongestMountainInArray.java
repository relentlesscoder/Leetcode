package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/25/2019.
 * #0845 https://leetcode.com/problems/longest-mountain-in-array/
 */
public class LongestMountainInArray {

	// time O(n), space O(m)
	public int longestMountain(int[] A) {
		if(A == null || A.length == 0){
			return 0;
		}
		int res = 0, n = A.length;
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 1; i < n - 1; i++){
			if(A[i] > A[i - 1] && A[i] > A[i + 1]){
				queue.offerLast(i);
			}
		}
		while(!queue.isEmpty()){
			int i = queue.pollFirst(), j = i, cur = 1, left = 0, right = 0;
			while(i > 0 && A[i] > A[i - 1]){
				left++;
				i--;
			}
			while(j < n - 1 && A[j] > A[j + 1]){
				right++;
				j++;
			}
			if(left > 0 && right > 0){
				res = Math.max(res, cur + left + right);
			}
		}
		return res;
	}
}
