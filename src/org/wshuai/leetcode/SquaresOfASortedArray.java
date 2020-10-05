package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0977 https://leetcode.com/problems/squares-of-a-sorted-array/
 */
public class SquaresOfASortedArray {

	// time O(n)
	public int[] sortedSquares(int[] A) {
		int n = A.length, left = 0, right = n - 1;
		int[] res = new int[n];
		for(int i = n - 1; i >= 0; i--){
			int leftSquare = A[left] * A[left];
			int rightSquare = A[right] * A[right];
			if(leftSquare >= rightSquare){
				res[i] = leftSquare;
				left++;
			}else{
				res[i] = rightSquare;
				right--;
			}
		}
		return res;
	}
}
