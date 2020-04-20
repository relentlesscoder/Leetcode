package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/13/2019.
 * #0753 https://leetcode.com/problems/cracking-the-safe/
 */
public class CrackingTheSafe {

	// time O(k^n)
	// https://leetcode.com/problems/cracking-the-safe/discuss/153039/DFS-with-Explanations
	// https://en.wikipedia.org/wiki/De_Bruijn_sequence
	public String crackSafe(int n, int k) {
		Set<String> visited = new HashSet<>();
		char[] arr = new char[n];
		Arrays.fill(arr, '0');
		String start = String.valueOf(arr);
		visited.add(start);
		StringBuilder cur = new StringBuilder(start);
		dfs(cur, visited, n, k, (int)Math.pow(k, n));
		return cur.toString();
	}

	private boolean dfs(StringBuilder cur, Set<String> visited, int n, int k, int count){
		if(visited.size() == count){
			return true;
		}
		String prev = cur.substring(cur.length() - n + 1);
		for(char i = '0'; i < '0' + k; i++){
			String next  = prev + i;
			if(!visited.contains(next)){
				cur.append(i);
				visited.add(next);
				if(dfs(cur, visited, n, k, count)){
					return true;
				}
				visited.remove(next);
				cur.deleteCharAt(cur.length() - 1);
			}
		}
		return false;
	}
}
