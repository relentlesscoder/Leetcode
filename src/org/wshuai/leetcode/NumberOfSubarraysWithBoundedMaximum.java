package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2019.
 * #0795 https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
 */
public class NumberOfSubarraysWithBoundedMaximum {

	// time O(n)
	// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/discuss/119162/Clean-and-simple-O(n)-Java
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
		int res = 0, last = -1, start = -1;
		for(int i = 0; i < A.length; i++){
			// reset start and last
			if(A[i] > R){
				start = last = i;
				continue;
			}
			if(A[i] >= L){
				last = i;
			}
			// number of valid subarrays end at i must
			// include the last seen valid max
			// for L = 3, R = 5, A = [0, 1, 3, 2, 1]
			// number of valid subarrays ends at index
			// 2, 3, 4 must include 3
			res += last - start;
		}
		return res;
	}
}
