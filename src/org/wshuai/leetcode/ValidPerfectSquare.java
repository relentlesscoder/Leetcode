package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2016.
 * #0367 https://leetcode.com/problems/valid-perfect-square/
 */
public class ValidPerfectSquare {
	// time O(log(n))
	public boolean isPerfectSquare(int num) {
		if(num == 1){
			return true;
		}
		long left = 1, right = num / 2;
		while(left < right){
			long mid = left + (right - left) / 2;
			if(mid * mid < num){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left*left == num;
	}
}
