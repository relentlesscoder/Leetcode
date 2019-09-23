package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/23/16.
 * #412 https://leetcode.com/problems/fizz-buzz/
 */
public class FizzBuzz {
	public List<String> fizzBuzz(int n) {
		List<String> lst = new ArrayList<String>();
		if (n < 1) {
			return lst;
		}
		for (int i = 1; i <= n; i++) {
			if (i % 15 == 0) {
				lst.add("FizzBuzz");
			} else if (i % 5 == 0) {
				lst.add("Buzz");
			} else if (i % 3 == 0) {
				lst.add("Fizz");
			} else {
				lst.add(Integer.toString(i));
			}
		}
		return lst;
	}
}
