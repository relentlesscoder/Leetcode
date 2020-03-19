package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/12/2019.
 * #0625 https://leetcode.com/problems/minimum-factorization/
 */
public class MinimumFactorization {
	// time O(log(a)), space O(log(a))
	public int smallestFactorization(int a) {
		if(a < 10){
			return a;
		}

		List<Integer> res = new ArrayList<>();
		// array list will be ordered by desc
		for(int i = 9; i > 1; i--){
			// always try to find the max factor
			while(a % i == 0){
				a /= i;
				res.add(i);
			}
		}

		// can't be fully divided
		if(a != 1){
			return 0;
		}

		long result = 0;
		for(int i = res.size() - 1; i >= 0; i--){
			result = result * 10 + res.get(i);
			if(result > Integer.MAX_VALUE){
				return 0;
			}
		}
		return (int)result;
	}
}
