package org.wshuai.leetcode;

/**
 * Created by Wei on 12/5/2019.
 * #600 https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {
	public int findIntegers(int num) {
		StringBuilder bin = new StringBuilder(Integer.toBinaryString(num)).reverse();
		int n = bin.length();
		// DP see https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
		int[] endWithOne = new int[n];
		int[] endWithZero = new int[n];
		endWithOne[0] = 1;
		endWithZero[0] = 1;
		for(int i = 1; i < n; i++){
			endWithOne[i] = endWithZero[i - 1];
			endWithZero[i] = endWithZero[i - 1] + endWithOne[i - 1];
		}
		int result = endWithZero[n - 1] + endWithOne[n - 1];
		// from MSB to LSB
		for (int i = n - 2; i >= 0; i--) {
			// if num has two consecutive 11 at current bit, then from the
			// current bbit then it is greater than all the numbers does not
			// have consecutive 1s
			if (bin.charAt(i) == '1' && bin.charAt(i + 1) == '1'){
				break;
			}
			// if num has two consecutive 00 at current bit, then minus
			// the number of numbers that end with 1 at this bit position
			// since these numbers are all greater than num
			if (bin.charAt(i) == '0' && bin.charAt(i + 1) == '0'){
				result -= endWithOne[i];
			}
		}
		return result;
	}
}
