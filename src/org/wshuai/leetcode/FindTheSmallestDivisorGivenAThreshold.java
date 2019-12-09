package org.wshuai.leetcode;

/**
 * Created by Wei on 12/9/2019.
 * #1283 https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 */
public class FindTheSmallestDivisorGivenAThreshold {
	public int smallestDivisor(int[] nums, int threshold) {
		int left = 1;
		int right = 1_000_000;
		while(left < right){
			int mid = (left + right) / 2;
			int sum = 0;
			for(int n : nums){
				// remember this integer division ceiling method
				sum += (n + mid - 1) / mid;
			}
			if(sum > threshold){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
