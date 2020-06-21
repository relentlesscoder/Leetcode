package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/08/2019.
 * #0728 https://leetcode.com/problems/self-dividing-numbers/
 */
public class SelfDividingNumbers {
	// time O(n*d)
	public List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> res = new ArrayList<>();
		for(int i = left; i <= right; i++){
			if(selfDeviding(i)){
				res.add(i);
			}
		}
		return res;
	}

	private boolean selfDeviding(int num){
		if(num < 10){
			return true;
		}
		for(int temp = num; temp > 0; temp /= 10){
			int d = temp % 10;
			if(d == 0 || num % d != 0){
				return false;
			}
		}
		return true;
	}
}
