package org.wshuai.leetcode;

/**
 * Created by Wei on 12/25/2019.
 * #0786 https://leetcode.com/problems/k-th-smallest-prime-fraction/
 */
public class KthSmallestPrimeFraction {
	// time O(n*log(max^2))
	// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
	public int[] kthSmallestPrimeFraction(int[] A, int K) {
		double low = 0.0, high = 1.0;
		int n = A.length, p = 0, q = 1;

		for(int count = 0; true; count = 0, p = 0){
			double mid = low + (high - low) / 2;
			// from left to right, find all fractions A[i]/A[j] <= m
			// no need to reset j for each i given the array is sorted
			for(int i = 0, j = 1; i < n; i++){
				while(j < n && A[i] > mid * A[j]){
					j++;
				}
				count += (n - j);
				// record the max fraction
				if(j < n && p * A[j] < q * A[i]){
					p = A[i];
					q = A[j];
				}
			}
			// if number of fraction less or equal to current middle value
			// we find the solution already
			if(count == K){
				return new int[]{p, q};
			}
			if(count > K){
				high = mid;
			}else{
				low = mid;
			}
		}
	}
}
