package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/15/2019.
 * #0792 https://leetcode.com/problems/number-of-matching-subsequences/
 */
public class NumberOfMatchingSubsequences {
	// time O(m*n), space O(n)
	// https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation
	public int numMatchingSubseq(String S, String[] words) {
		int res = 0;
		// waiting records the last matched character
		// and the next index to match for each word
		List<Integer[]>[] waiting = new ArrayList[26];
		for(int i = 0; i < 26; i++){
			waiting[i] = new ArrayList<>();
		}
		for(int i = 0; i < words.length; i++){
			waiting[words[i].charAt(0) - 'a'].add(new Integer[]{i, 1});
		}
		for(char c : S.toCharArray()){
			List<Integer[]> cur = new ArrayList<>(waiting[c - 'a']);
			waiting[c - 'a'].clear();
			for(Integer[] arr : cur){
				int index = arr[0], next = arr[1];
				// no more characters to match for current word
				if(next == words[index].length()){
					res++;
					continue;
				}
				// still has unmatched characters, save it to waiting
				waiting[words[index].charAt(next) - 'a'].add(new Integer[]{index, next + 1});
			}
		}
		return res;
	}
}
