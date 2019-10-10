package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/31/2019.
 * #763 https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {
	public List<Integer> partitionLabels(String S) {
		int[] last = new int[26];
		for (int i = 0; i < S.length(); i++) {
			last[S.charAt(i) - 'a'] = i;
		}

		int j = 0;
		int anchor = 0;
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < S.length(); i++) {
			// find the last index of the current character to extend the substring as long as possible
			j = Math.max(j, last[S.charAt(i) - 'a']);
			if (i == j) {
				ans.add(i - anchor + 1);
				anchor = i + 1;
			}
		}
		return ans;
	}
}
