package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/23/2016.
 * #0412 https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {
	// time O(n)
	public List<String> fizzBuzz(int n) {
		List<String> res = new ArrayList<>();
		for(int i = 1; i <= n; i++){
			int m1 = i % 3, m2 = i % 5;
			if(m1 == 0 && m2 == 0){
				res.add("FizzBuzz");
			}else if(m1 == 0){
				res.add("Fizz");
			}else if(m2 == 0){
				res.add("Buzz");
			}else{
				res.add(Integer.toString(i));
			}
		}
		return res;
	}
}
