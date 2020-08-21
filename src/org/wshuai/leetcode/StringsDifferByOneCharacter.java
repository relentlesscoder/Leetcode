package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 08/20/2020.
 * #1554 https://leetcode.com/problems/strings-differ-by-one-character/
 */
public class StringsDifferByOneCharacter {

	// time O(n*m), space O(n*m)
	public boolean differByOne(String[] dict) {
		int n = dict.length;
		if(n <= 1){
			return false;
		}
		int l = dict[0].length();
		Set<String>[] sets = new HashSet[l];
		for(int i = 0; i < l; i++){
			sets[i] = new HashSet<String>();
		}
		for(int i = 0; i < n; i++){
			char[] cur = dict[i].toCharArray();
			for(int j = 0; j < l; j++){
				char val = cur[j];
				cur[j] = '#';
				String key = new String(cur);
				if(!sets[j].add(key)){
					return true;
				}
				cur[j] = val;
			}
		}
		return false;
	}
}
