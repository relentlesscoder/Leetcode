package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 02/07/2020.
 * #0349 https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class IntersectionOfTwoArrays {

	// time O(n), space O(n)
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
		for(int i : nums1){
			set1.add(i);
		}
		for(int i : nums2){
			if(set1.contains(i)){
				set2.add(i);
			}
		}
		int[] res = new int[set2.size()];
		int j = 0;
		for(int s : set2){
			res[j++] = s;
		}
		return res;
	}
}
