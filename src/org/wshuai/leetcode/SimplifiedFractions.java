package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 05/17/2020.
 * #1447 https://leetcode.com/problems/simplified-fractions/
 */
public class SimplifiedFractions {

	// time O(n^2), space O(n^2)
	public List<String> simplifiedFractions(int n) {
		Set<String> set = new HashSet<>();
		for(int i = 2; i <= n; i++){
			for(int j = 1; j < i; j++){
				int gcd = gcd(i, j);
				set.add(j / gcd + "/" + i / gcd);
			}
		}
		return new ArrayList<>(set);
	}

	private int gcd(int x, int y){
		return y == 0 ? x : gcd(y, x % y);
	}
}
