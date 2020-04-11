package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/29/2020.
 * #1397 https://leetcode.com/problems/find-all-good-strings/
 */
public class FindAllGoodStrings {
	private static final int MOD = (int)1e9 + 7;

	public int findGoodStrings(int n, String s1, String s2, String evil) {
		char[] arr = evil.toCharArray();
		return dfs(0, 0, true, true, n,
			s1.toCharArray(), s2.toCharArray(), arr, computeLPS(arr), new HashMap<String, Integer>());
	}

	private int dfs(int i, int maxCommonPrefix, boolean isPrefixS1, boolean isPrefixS2,
	                int n, char[] s1, char[] s2, char[] evil, int[] lps, Map<String, Integer> map){
		if(maxCommonPrefix == evil.length){
			return 0;
		}
		if(i == n){
			return 1;
		}
		String key = i + "_" + maxCommonPrefix + "_" + isPrefixS1 + "_" + isPrefixS2;
		if(map.containsKey(key)){
			return map.get(key);
		}
		int res = 0;
		char from = isPrefixS1 ? s1[i] : 'a', to = isPrefixS2 ? s2[i] : 'z';
		for(char c = from; c <= to; c++){
			int j = maxCommonPrefix;
			while(j > 0 && evil[j] != c){
				j = lps[j - 1];
			}
			if(c == evil[j]){
				j++;
			}
			res += dfs(i + 1, j, isPrefixS1 && (c == from),
				isPrefixS2 && (c == to), n, s1, s2, evil, lps, map);
			res %= MOD;
		}
		map.put(key, res);
		return res;
	}

	private int[] computeLPS(char[] str){
		int n = str.length, j = 0;
		int[] lps = new int[n];
		lps[0] = 0;
		for(int i = 1; i < n; i++){
			while(j > 0 && str[i] != str[j]){
				j = lps[j - 1];
			}
			if(str[i] == str[j]){
				j++;
			}
			lps[i] = j;
		}
		return lps;
	}
}
