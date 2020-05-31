package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 05/31/2020.
 * #1461 https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
	
	public boolean hasAllCodes(String s, int k) {
		int n = s.length();
		Set<String> set = new HashSet<>();
		for(int i = 0; i <= n - k; i++){
			set.add(s.substring(i, i + k));
		}
		return set.size() == (1 << k);
	}
}
