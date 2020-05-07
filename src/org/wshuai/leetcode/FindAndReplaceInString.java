package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/19/2019.
 * #0833 https://leetcode.com/problems/find-and-replace-in-string/
 */
public class FindAndReplaceInString {
	// time O(n^2)
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		List<int[]> sorted = new ArrayList<>();
		for(int i = 0; i < indexes.length; i++){
			sorted.add(new int[]{indexes[i], i});
		}
		Collections.sort(sorted, (a, b) -> b[0] - a[0]);
		for(int[] in : sorted){
			int start = in[0], end = start + sources[in[1]].length();
			if(S.substring(start, end).equals(sources[in[1]])){
				S = S.substring(0, start) + targets[in[1]] + S.substring(end);
			}
		}
		return S;
	}
}
