package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2016.
 * #0320 https://leetcode.com/problems/generalized-abbreviation/
 */
public class GeneralizedAbbreviation {
	// time O(n!)
	public List<String> generateAbbreviations(String word) {
		List<String> res = new ArrayList<>();
		dfs(word, 0, "", 0, res);
		return res;
	}

	private void dfs(String word, int start, String cur, int count, List<String> res){
		if(start == word.length()){
			if(count > 0){
				cur += count;
			}
			res.add(cur);
			return;
		}
		// abbreviate the current character
		dfs(word, start + 1, cur, count + 1, res);
		// not abbreviate it
		dfs(word, start + 1, cur + (count > 0 ? count : "") + word.charAt(start), 0, res);
	}
}
