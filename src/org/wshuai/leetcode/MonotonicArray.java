package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0896 https://leetcode.com/problems/monotonic-array/
 */
public class MonotonicArray {

	// time O(n)
	public boolean isMonotonic(int[] A) {
		if(A.length < 3){
			return true;
		}
		int[] count = new int[2];
		for(int i = 1; i < A.length; i++){
			if(A[i] == A[i - 1]){
				continue;
			}
			if(A[i] > A[i - 1]){
				count[0]++;
			}else{
				count[1]++;
			}
		}
		return count[0] == 0 || count[1] == 0;
	}
}
