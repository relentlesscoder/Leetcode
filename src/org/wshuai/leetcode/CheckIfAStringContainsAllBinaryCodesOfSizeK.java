package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 05/31/2020.
 * #1461 https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {

	// time O(n)
	public boolean hasAllCodes(String s, int k) {
		int n = s.length(), v = 0, m = (1 << k) - 1;
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < n; i++){
			v = (v << 1) + (s.charAt(i) - '0');
			v &= m;
			if(i >= k - 1){
				set.add(v);
			}
		}
		return set.size() == (1 << k);
	}
}
