package org.wshuai.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/15/2019.
 * #792 https://leetcode.com/problems/number-of-matching-subsequences/
 */
public class NumberOfMatchingSubsequences {
	// https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation
	public int numMatchingSubseq(String S, String[] words) {
		List<Integer[]>[] waiting = new List[128];
		for(int c = 0; c <= 'z'; c++){
			waiting[c] = new ArrayList();
		}
		for(int i = 0; i < words.length; i++){
			waiting[words[i].charAt(0)].add(new Integer[]{i, 1});
		}
		for(char c: S.toCharArray()){
			List<Integer[]> advance = new ArrayList(waiting[c]);
			waiting[c].clear();
			for(Integer[] a : advance){
				waiting[a[1] < words[a[0]].length() ? words[a[0]].charAt(a[1]++) : 0].add(a);
			}
		}
		return waiting[0].size();
	}
}
