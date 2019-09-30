package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 9/30/2019.
 * #1055 https://leetcode.com/problems/shortest-way-to-form-string/
 */
public class ShortestWayToFormString {
	public int shortestWay(String source, String target) {
		int res = 0;
		int i = 0;
		int j = 0;
		Set<Character> set = new HashSet<>();
		char[] S = source.toCharArray();
		char[] T = target.toCharArray();
		for(char s: S){
			set.add(s);
		}
		for(char t: T){
			if(!set.contains(t)){
				return -1;
			}
		}
		while(j < T.length){
			if(i >= S.length){
				i = 0;
				res++;
			}
			if(S[i] == T[j]){
				i++;
				j++;
			}else{
				i++;
			}
		}
		return res + 1;
	}
}
