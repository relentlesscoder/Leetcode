package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 09/19/2016.
 * #0288 https://leetcode.com/problems/unique-word-abbreviation/
 */
public class UniqueWordAbbreviation {

	private Map<String, Set<String>> abbrMap;

	public UniqueWordAbbreviation(String[] dictionary) {
		abbrMap = new HashMap<>();
		for(String word : dictionary){
			String abbr = getAbbr(word);
			abbrMap.putIfAbsent(abbr, new HashSet<>());
			abbrMap.get(abbr).add(word);
		}
	}

	public boolean isUnique(String word) {
		String abbr = getAbbr(word);
		return !abbrMap.containsKey(abbr)
				|| (abbrMap.get(abbr).size() == 1 && abbrMap.get(abbr).iterator().next().equals(word));
	}

	private String getAbbr(String word){
		return word.length() <= 2 ? word : "" + word.charAt(0)
				+ Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
	}
}
