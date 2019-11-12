package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/31/2019.
 * #916 https://leetcode.com/problems/word-subsets/
 */
public class WordSubsets {
	public List<String> wordSubsets(String[] A, String[] B) {
		List<String> res = new ArrayList<>();
		int[] max = new int[26];
		int[] cnt = new int[26];
		for(String s : B){
			reset(cnt);
			for(char c: s.toCharArray()){
				cnt[c-'a']++;
			}
			for(int i = 0; i < 26; i++){
				max[i] = Math.max(cnt[i], max[i]);
			}
		}
		for(String a : A){
			reset(cnt);
			for(char c: a.toCharArray()){
				cnt[c-'a']++;
			}
			boolean uni = true;
			for(int i = 0; i < 26; i++){
				if(cnt[i] < max[i]){
					uni = false;
					break;
				}
			}
			if(uni){
				res.add(a);
			}
		}
		return res;
	}

	private void reset(int[] cnt){
		for(int i = 0; i < cnt.length; i++){
			cnt[i] = 0;
		}
	}
}
