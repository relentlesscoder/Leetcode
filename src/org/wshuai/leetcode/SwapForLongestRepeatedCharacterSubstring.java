package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/31/2019.
 * #1156 https://leetcode.com/problems/swap-for-longest-repeated-character-substring/
 */
public class SwapForLongestRepeatedCharacterSubstring {
	public int maxRepOpt1(String text) {
		int[] count = new int[26];
		List<int[]> intervals = new ArrayList<>();
		char[] arr = (text + "#").toCharArray();
		char curr = arr[0];
		int len = 1;
		for(int i = 1; i < arr.length; i++){
			if(arr[i] != curr){
				intervals.add(new int[]{len, curr - 'a'});
				count[curr - 'a'] += len;
				len = 1;
				curr = arr[i];
			}else{
				len++;
			}
		}
		if(intervals.size() == 1){
			return text.length();
		}
		int res = 0;
		for(int[] itv: intervals){
			int sum = itv[0] == count[itv[1]] ? itv[0] : itv[0] + 1;
			res = Math.max(res, sum);
		}
		for(int i = 1; i < intervals.size() - 1; i++){
			int[] itv = intervals.get(i);
			if(itv[0] == 1){
				int[] prev = intervals.get(i - 1);
				int[] next = intervals.get(i + 1);
				if(prev[1] == next[1]){
					int sum = prev[0] + next[0];
					res = Math.max(sum == count[prev[1]] ? sum : sum + 1, res);
				}
			}
		}
		return res;
	}
}
