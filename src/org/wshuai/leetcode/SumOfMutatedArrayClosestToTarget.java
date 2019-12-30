package org.wshuai.leetcode;

/**
 * Created by Wei on 12/30/2019.
 * #1300 https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/
 */
public class SumOfMutatedArrayClosestToTarget {
	public int findBestValue(int[] arr, int target) {
		int sum = 0;
		int max = arr[0];
		for(int a : arr){
			sum += a;
			max = Math.max(max, a);
		}
		if(sum <= target){
			return max;
		}
		int left = 1, right = max;
		// find the left bound with sum greater or equal to target
		// left and left - 1 are possible solutions
		while(left < right){
			int mid = left + (right - left) / 2;
			int s = 0;
			for(int a : arr){
				s += a > mid ? mid : a;
			}
			if(s < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		int s1 = 0;
		int s2 = 0;
		for(int a : arr){
			s1 += a > left ? left : a;
			s2 += a > (left - 1) ? (left - 1) : a;
		}
		return (Math.abs(s2 - target) <= Math.abs(s1 - target)) ? left - 1 : left;
	}
}
