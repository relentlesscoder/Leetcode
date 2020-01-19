package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1317 https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
 */
public class ConvertIntegerToTheSumOfTwoNoZeroIntegers {
	// time O(n)
	public int[] getNoZeroIntegers(int n) {
		for(int i = 1; i < n; i++){
			if(nonZero(i) && nonZero(n - i)){
				return new int[]{i, n - i};
			}
		}
		return new int[]{-1, -1};
	}

	private boolean nonZero(int n){
		while(n > 0){
			if(n % 10 == 0){
				return false;
			}
			n /= 10;
		}
		return true;
	}
}
