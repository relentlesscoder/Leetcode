package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 10/14/2019.
 * #616 https://leetcode.com/problems/add-bold-tag-in-string/
 */
public class AddBoldTagInString {
	public String addBoldTag(String s, String[] dict) {
		List<int[]> intervals = new ArrayList<>();
		for(String word: dict){
			int start = 0;
			int next;
			while((next = s.indexOf(word, start)) != -1){
				intervals.add(new int[]{next, next + word.length() - 1});
				start = next + 1;
			}
		}
		Collections.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int k = 0;
		while(i < intervals.size()){
			int[] curr = intervals.get(i);
			sb.append(s.substring(k, curr[0]));
			int j = i + 1;
			while(j < intervals.size() && intervals.get(j)[0] <= curr[1] + 1){
				curr[1] = Math.max(intervals.get(j)[1], curr[1]);
				j++;
			}
			sb.append("<b>" + s.substring(curr[0], curr[1] + 1) + "</b>");
			i = j;
			k = curr[1] + 1;
		}
		if(k < s.length()){
			sb.append(s.substring(k));
		}
		return sb.toString();
	}
}
