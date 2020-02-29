package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/18/2019.
 * #0472 https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {
	// time O(n*l), space O(26*l)
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		TrieNode root = new TrieNode();
		List<String> res = new ArrayList<>();
		Arrays.sort(words, (a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));
		for(String word : words){
			if(search(word, 0, root, root)){
				res.add(word);
			}else{
				insert(word, root);
			}
		}
		return res;
	}

	private boolean search(String word, int start, TrieNode cur, TrieNode root){
		int n = word.length();
		for(int i = start; i < n; i++){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				return false;
			}
			cur = cur.get(c);
			if(cur.isEnd() && i != n - 1){
				if(search(word, i + 1, cur, root) || search(word, i + 1, root, root)){
					return true;
				}
			}
		}
		return cur.isEnd();
	}

	private void insert(String word, TrieNode root){
		TrieNode cur = root;
		for(char c : word.toCharArray()){
			if(!cur.containsKey(c)){
				cur.put(c, new TrieNode());
			}
			cur = cur.get(c);
		}
		cur.setEnd();
	}

	// time O(n*l^2)
	public List<String> findAllConcatenatedWordsInADictDP(String[] words) {
		List<String> res = new ArrayList<>();
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		Set<String> dict = new HashSet<>();
		for(String s : words){
			if(canForm(s, dict)){
				res.add(s);
			}
			dict.add(s);
		}
		return res;
	}

	private boolean canForm(String word, Set<String> dict){
		if (dict.isEmpty()){
			return false;
		}
		int n = word.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (!dp[j]){
					continue;
				}
				if (dict.contains(word.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}
}
