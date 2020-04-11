package org.wshuai.leetcode;

/**
 * Created by Wei on 04/06/2020.
 * #1404 https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 */
public class NumberOfStepsToReduceANumberInBinaryRepresentationToOne {
	// time O(n)
	// https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/discuss/564287/C%2B%2BJava-O(n)
	public int numSteps(String s) {
		int res = 0, n = s.length(), carry = 0;
		for(int i = n - 1; i > 0; i--){
			res++;
			if(s.charAt(i) - '0' + carry == 1){
				res++;
				carry = 1;
			}
		}
		// if carry is 1, since the MSB is 1 we need
		// one more operation to get 1
		return res + carry;
	}
}
