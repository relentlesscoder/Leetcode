package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 09/04/2020.
 * #1520 https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/
 */
public class MaximumNumberOfNonOverlappingSubstrings {

	// time O(n), space O(n)
	public List<String> maxNumOfSubstrings(String s) {
		List<String> res = new ArrayList<>();
		int n = s.length();
		int[] min = new int[26], max = new int[26];
		Arrays.fill(min, n);
		for(int i = 0; i < n; i++){
			int index = s.charAt(i) - 'a';
			min[index] = Math.min(min[index], i);
			max[index] = i;
		}
		int right = -1;
		for(int i = 0; i < n; i++){
			int index = s.charAt(i) - 'a';
			if(i == min[index]){
				int currentRight = findRight(s, min, max, i);
				if(currentRight != -1){
					if(i > right){
						res.add("");
					}
					right = currentRight;
					res.set(res.size() - 1, s.substring(i, right + 1));
				}
			}
		}
		return res;
	}

	private int findRight(String s, int[] min, int[] max, int i){
		int right = max[s.charAt(i) - 'a'];
		for(int j = i; j <= right; j++){
			if(min[s.charAt(j) - 'a'] < i){
				return -1;
			}
			right = Math.max(right, max[s.charAt(j) - 'a']);
		}
		return right;
	}
}
