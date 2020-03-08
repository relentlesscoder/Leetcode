package org.wshuai.leetcode;

/**
 * Created by Wei on 02/12/2020.
 * #1342 https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 */
public class NumberOfStepsToReduceANumberToZero {
	// time O(n)
	public int numberOfSteps (int num) {
		int res = 0;
		while(num > 0){
			if(num % 2 == 0){
				num /= 2;
			}else{
				num -= 1;
			}
			res++;
		}
		return res;
	}
}
