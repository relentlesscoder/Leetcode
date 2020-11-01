package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/29/2016.
 * #0140 https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakII {

	// time O(n^3), space O(n^2)
	public List<String> wordBreak(String s, List<String> wordDict) {
		return dfs(s, new HashSet<>(wordDict), new HashMap<String, List<String>>());
	}

	private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> dp){
		if(dp.containsKey(s)){
			return dp.get(s);
		}
		List<String> res = new ArrayList<>();
		int n = s.length();
		if(dict.contains(s)){
			res.add(s);
		}
		for(int i = 1; i < n; i++){
			String cur = s.substring(i);
			if(dict.contains(cur)){
				List<String> strs = dfs(s.substring(0, i), dict, dp);
				for(String str : strs){
					res.add(str + " " + cur);
				}
			}
		}
		dp.put(s, res);
		return res;
	}
}
