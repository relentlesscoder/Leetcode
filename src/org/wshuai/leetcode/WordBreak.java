package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 08/21/2016.
 * #0139 https://leetcode.com/problems/word-break/
 */
public class WordBreak {

	// time O(n^3), space O(n)
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		int n = s.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for(int i = 1; i <= n; i++){
			for(int j = i - 1; j >= 0; j--){
				if(dp[j] && dict.contains(s.substring(j, i))){
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}
}
