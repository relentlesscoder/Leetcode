package org.wshuai.leetcode;

/**
 * Created by Wei on 04/04/2017.
 * #0507 https://leetcode.com/problems/perfect-number/
 */
public class PerfectNumber {
	// time O(log(n))
	public boolean checkPerfectNumber(int num) {
		// note that 1 is a special case
		if(num <= 1){
			return false;
		}
		int sum = 1, div = 0;
		for(int i = 2; i*i <= num; i++){
			if(num % i == 0){
				sum += i;
				div = num / i;
				if(div != i){
					sum += div;
				}
			}
		}
		return sum == num;
	}
}
