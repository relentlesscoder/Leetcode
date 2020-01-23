package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/26/2016.
 * #0202 https://leetcode.com/problems/happy-number/
 */
public class HappyNumber {
	public boolean isHappy(int n) {
		Set<Integer> set = new HashSet<>();
		while(!set.contains(n)){
			set.add(n);
			n = happy(n);
			if(n == 1){
				return true;
			}
		}
		return false;
	}

	private int happy(int n){
		int res = 0;
		while(n > 0){
			int d = n % 10;
			res += d * d;
			n /= 10;
		}
		return res;
	}
}
