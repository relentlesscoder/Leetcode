package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/18/2019.
 * #0472 https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {

	private static TrieNode root;

	// time O(n*l)
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		List<String> res = new ArrayList<>();
		if(words == null || words.length == 0){
			return res;
		}
		root = new TrieNode();
		Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
		for(int i = 0; i < words.length; i++){
			if(search(words[i], 0, root)){
				res.add(words[i]);
			}else{
				insert(words[i]);
			}
		}
		return res;
	}

	private boolean search(String word, int start, TrieNode cur){
		for(int i = start; i < word.length(); i++){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				return false;
			}
			cur = cur.get(c);
			if(cur.isEnd() && i != word.length() - 1){
				if(search(word, i + 1, cur) || search(word, i + 1, root)){
					return true;
				}
			}
		}
		return cur.isEnd();
	}

	private void insert(String word){
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				cur.put(c, new TrieNode());
			}
			cur = cur.get(c);
		}
		cur.setEnd();
	}

	private class TrieNode{

		private static final int R = 26;

		private TrieNode[] links;

		private boolean isEnd;

		private TrieNode(){
			links = new TrieNode[R];
			isEnd = false;
		}

		private TrieNode get(char key){
			return links[key - 'a'];
		}

		private void put(char key, TrieNode node){
			links[key - 'a'] = node;
		}

		private boolean containsKey(char key){
			return links[key - 'a'] != null;
		}

		private boolean isEnd(){
			return isEnd;
		}

		private void setEnd(){
			isEnd = true;
		}
	}

	// time O(n*l^2), space O(n*l)
	public List<String> findAllConcatenatedWordsInADictDP(String[] words) {
		List<String> res = new ArrayList<>();
		if(words == null || words.length == 0){
			return res;
		}
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		Set<String> dictionary = new HashSet<>();
		for(int i = 0; i < words.length; i++){
			if(canForm(words[i], dictionary)){
				res.add(words[i]);
			}
			dictionary.add(words[i]);
		}
		return res;
	}

	private boolean canForm(String s, Set<String> dictionary){
		if(s.equals("")){
			return false;
		}
		int n = s.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for(int i = 1; i <= n; i++){
			for(int j = i - 1; j >= 0; j--){
				if(dp[j] && dictionary.contains(s.substring(j, i))){
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}
}
