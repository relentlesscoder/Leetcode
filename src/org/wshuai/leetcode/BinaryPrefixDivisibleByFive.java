package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/8/19.
 * #1018 https://leetcode.com/problems/binary-prefix-divisible-by-5/
 */
public class BinaryPrefixDivisibleByFive {
	public List<Boolean> prefixesDivBy5(int[] A) {
		int num = 0;
		List<Boolean> res = new ArrayList<>();
		for(int i = 0; i < A.length; i++){
			num = (num << 1) + A[i];
			res.add(num % 5 == 0);
			num = num % 5;
		}
		return res;
	}
}
