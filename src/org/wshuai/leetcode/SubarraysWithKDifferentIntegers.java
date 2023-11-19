package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2019.
 * #0992 https://leetcode.com/problems/subarrays-with-k-different-integers/
 */
public class SubarraysWithKDifferentIntegers {

	// time O(n), space O(n)
	// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/234482/JavaC%2B%2BPython-Sliding-Window-atMost(K)-atMost(K-1)
	public int subarraysWithKDistinct(int[] A, int K) {
		return atMostK(A, K) - atMostK(A, K - 1);
	}

	// time O(n), space O(n)
	// #0340
	public int atMostK(int[] A, int k) {
		int res = 0, n = A.length;
		int[] count = new int[A.length + 1];
		for(int i = 0, j = 0, distinct = 0; j < n; j++){
			if(count[A[j]]++ == 0){
				distinct++;
			}
			if(distinct > k){
				while(--count[A[i++]] > 0);
				distinct--;
			}
			// sub arrays end with A[j]
			res += j - i + 1;
		}
		return res;
	}
}
