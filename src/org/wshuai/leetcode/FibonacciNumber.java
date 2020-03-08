package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0509 https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
	// time O(n), space O(1)
	public int fib(int N) {
		if(N < 2){
			return N;
		}
		int first = 0, second = 1, temp = 0;
		for(int i = 2; i <= N; i++){
			temp = first + second;
			first = second;
			second = temp;
		}
		return second;
	}
}
