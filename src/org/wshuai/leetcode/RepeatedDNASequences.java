package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 01/07/2020.
 * #0187 https://leetcode.com/problems/repeated-dna-sequences/
 */
public class RepeatedDNASequences {

	// time O(n - l), space O(n - l)
	public List<String> findRepeatedDnaSequences(String s) {
		Set<String> res = new HashSet<>();
		Set<Integer> visited = new HashSet<>();
		int mask = (1 << 20) - 1;
		int cur = 0;
		for(int i = 0; i < s.length(); i++){
			int val = mapToInt(s.charAt(i));
			cur = ((cur << 2) + val) & mask;
			if(i >= 9 && !visited.add(cur)){
				res.add(s.substring(i - 9, i + 1));
			}
		}
		return new ArrayList<String>(res);
	}

	// time O(n - l), space O(n - l)
	public List<String> findRepeatedDnaSequencesRabinKarp(String s) {
		Set<String> res = new HashSet<>();
		Set<Integer> visited = new HashSet<>();
		int d = 4;
		// pre-calculate to facilitate the rehashing
		int pow = (int)Math.pow(d, 9);
		int hash = 0;
		// initialize the first hash
		for(int i = 0; i < Math.min(10, s.length()); i++){
			hash = hash * d + mapToInt(s.charAt(i));
		}
		visited.add(hash);
		for(int i = 10; i < s.length(); i++){
			// rolling hash
			hash = (hash - mapToInt(s.charAt(i - 10))*pow)*d + mapToInt(s.charAt(i));
			if(!visited.add(hash)){
				res.add(s.substring(i - 9, i + 1));
			}
		}
		return new ArrayList<>(res);
	}

	private int mapToInt(char c){
		if(c == 'A'){
			return 0;
		}
		if(c == 'C'){
			return 1;
		}
		if(c == 'G'){
			return 2;
		}
		return 3;
	}
}
