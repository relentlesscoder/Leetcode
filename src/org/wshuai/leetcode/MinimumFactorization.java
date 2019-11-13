package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/12/19.
 * #625 https://leetcode.com/problems/minimum-factorization/
 */
public class MinimumFactorization {
	public int smallestFactorization(int a) {
		if(a < 10){
			return a;
		}

		List<Integer> res = new ArrayList<>();
		// array list will be ordered by desc
		for(int i = 9; i > 1; i--){
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
