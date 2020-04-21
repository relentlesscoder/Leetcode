package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/31/2019.
 * #0763 https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {
	// time O(n)
	public List<Integer> partitionLabels(String S) {
		List<Integer> res = new ArrayList<>();
		int n = S.length();
		int[] map = new int[26];
		for(int i = 0; i < n; i++){
			map[S.charAt(i) - 'a'] = i;
		}
		for(int i = 0, j = 0, k = 0; i < n; i++){
			// find the last index of the current character to extend the substring as long as possible
			k = Math.max(k, map[S.charAt(i) - 'a']);
			if(k == i){
				res.add(i - j + 1);
				j = i + 1;
			}
		}
		return res;
	}
}
