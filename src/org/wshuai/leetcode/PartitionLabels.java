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
		int[] rightBounds = new int[26];
		for(int i = 0; i < n; i++){
			rightBounds[S.charAt(i) - 'a'] = i;
		}
		for(int i = 0, j = 0, k = 0; j < n; j++){
			k = Math.max(k, rightBounds[S.charAt(j) - 'a']);
			if(k == j){
				res.add(j - i + 1);
				i = j + 1;
			}
		}
		return res;
	}
}
