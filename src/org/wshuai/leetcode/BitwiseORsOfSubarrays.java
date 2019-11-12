package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/10/2019.
 * #898 https://leetcode.com/problems/bitwise-ors-of-subarrays/
 */
public class BitwiseORsOfSubarrays {
	public int subarrayBitwiseORs(int[] A) {
		Set<Integer> res = new HashSet<>();
		Set<Integer> cur = new HashSet<>();
		Set<Integer> cur2;
		for(Integer i : A){
			cur2 = new HashSet<>();
			cur2.add(i);
			for(Integer j : cur){
				cur2.add(i | j);
			}
			cur = cur2;
			res.addAll(cur);
		}
		return res.size();
	}
}
