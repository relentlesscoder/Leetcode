package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/19/2019.
 * #966 https://leetcode.com/problems/vowel-spellchecker/
 */
public class VowelSpellchecker {
	private Set<String> exact;
	private HashMap<String, String> cap;
	private HashMap<String, String> vow;

	public String[] spellchecker(String[] wordlist, String[] queries) {
		exact = new HashSet<>();
		cap = new HashMap<>();
		vow = new HashMap<>();
		for(String word: wordlist){
			exact.add(word);
			cap.putIfAbsent(word.toLowerCase(), word);
			vow.putIfAbsent(processVowel(word.toLowerCase()), word);
		}
		String[] res = new String[queries.length];
		int i = 0;
		for(String q: queries){
			res[i++] = tryMatch(q);
		}
		return res;
	}

	private String processVowel(String word){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < word.length()){
			char c = word.charAt(i);
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
				sb.append('*');
			}else{
				sb.append(c);
			}
			i++;
		}
		return sb.toString();
	}

	private String tryMatch(String query){
		if(exact.contains(query)){
			return query;
		}
		String lower = query.toLowerCase();
		if(cap.containsKey(lower)){
			return cap.get(lower);
		}
		String processed = processVowel(lower);
		if(vow.containsKey(processed)){
			return vow.get(processed);
		}
		return "";
	}
}
