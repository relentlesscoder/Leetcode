package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2020.
 * #0069 https://leetcode.com/problems/sqrtx/
 */
public class SqrtX {
	// time log(n)
	public int mySqrt(int x) {
		if(x == 0){
			return 0;
		}
		int left = 1, right = x;
		while(left < right){
			int mid = left + (right - left + 1) / 2;
			if(mid > x / mid){
				right = mid - 1;
			}else{
				left = mid;
			}
		}
		return left;
	}
}
