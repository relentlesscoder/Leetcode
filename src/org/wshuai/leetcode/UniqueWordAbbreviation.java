package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/19/2016.
 * #0288 https://leetcode.com/problems/unique-word-abbreviation/
 */
public class UniqueWordAbbreviation {
	private final Map<String, Boolean> abbrDict = new HashMap<String, Boolean>();
	private final Set<String> dict;

	public UniqueWordAbbreviation(String[] dictionary) {
		// the input may contains duplicate
		dict = new HashSet<String>(Arrays.asList(dictionary));
		for (String s : dict) {
			String abbr = toAbbr(s);
			// may contain duplicate abbreviation in the dictionary
			abbrDict.put(abbr, !abbrDict.containsKey(abbr));
		}
	}

	public boolean isUnique(String word) {
		String abbr = toAbbr(word);
		Boolean hasAbbr = abbrDict.get(abbr);
		return hasAbbr == null || (hasAbbr && dict.contains(word));
	}

	private String toAbbr(String s) {
		int n = s.length();
		if (n <= 2) {
			return s;
		}
		return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
	}
}
