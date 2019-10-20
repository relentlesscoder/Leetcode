package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/2019.
 * #978 https://leetcode.com/problems/longest-turbulent-subarray/
 */
public class LongestTurbulentSubarray {
	public int maxTurbulenceSize(int[] A) {
		if(A.length == 1){
			return A.length;
		}
		int i = 0;
		int j = 1;
		int flag = A[1] > A[0] ? 1 : (A[1] == A[0] ? 0 : -1);
		int max = flag == 0 ? 1 : 2;
		while(j < A.length){
			int curr = A[j] > A[j - 1] ? 1 : (A[j] == A[j - 1] ? 0 : -1);
			int prod = curr * flag;
			if(curr == 0){
				i = j;
			}else if(prod == -1){
				max = Math.max(max, j - i + 1);
			}else{
				i = j - 1;
			}
			flag = curr;
			j++;
		}
		return max;
	}
}
