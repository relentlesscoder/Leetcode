package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/19.
 * #1053 https://leetcode.com/problems/previous-permutation-with-one-swap/
 */
public class PreviousPermutationWithOneSwap {
	// https://leetcode.com/problems/previous-permutation-with-one-swap/discuss/299215/JavaC%2B%2BPython-When-Leetcode-Was-Wrong
	public int[] prevPermOpt1(int[] A) {
		int N = A.length;

		int i = N - 2;
		while(i >= 0 && A[i] <= A[i + 1]){
			i--;
		}

		if(i < 0){
			return A;
		}

		int j = N - 1;
		while(A[i] <= A[j]){
			j--;
		}
		while(A[j - 1] == A[j]){
			j--;
		}
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		return A;
	}
}
