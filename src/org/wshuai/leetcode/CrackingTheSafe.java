package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/13/2019.
 * #753 https://leetcode.com/problems/cracking-the-safe/
 */
public class CrackingTheSafe {
	private Set<String> visited;
	private List<Integer> edges;

	// https://leetcode.com/problems/cracking-the-safe/discuss/153039/DFS-with-Explanations
	// https://en.wikipedia.org/wiki/De_Bruijn_sequence
	public String crackSafe(int n, int k) {
		visited = new HashSet<>();
		edges = new ArrayList<>();

		char[] arr = new char[n];
		Arrays.fill(arr, '0');
		String startingNode = new String(arr);
		visited.add(startingNode);
		StringBuilder res = new StringBuilder(startingNode);
		int count = (int)Math.pow(k, n);
		dfs(k, n, res, count);
		return res.toString();
	}

	private boolean dfs(int k, int n, StringBuilder res, int count){
		if(visited.size() == count){
			return true;
		}
		String lastDigits = res.substring(res.length() - n + 1);
		for(char i = '0'; i < '0' + k; i++){
			String nextNode = lastDigits + i;
			if(!visited.contains(nextNode)){
				res.append(i);
				visited.add(nextNode);
				if(dfs(k, n, res, count)){
					return true;
				}
				visited.remove(nextNode);
				res.deleteCharAt(res.length() - 1);
			}
		}
		return false;
	}
}
