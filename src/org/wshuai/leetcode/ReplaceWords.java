package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/08/2019.
 * #0648 https://leetcode.com/problems/replace-words/
 */
public class ReplaceWords {
	// time O(n*d), space O(26*d)
	public String replaceWords(List<String> dict, String sentence) {
		TrieNode trie = new TrieNode();
		for(String root : dict){
			insert(root, trie);
		}
		String[] words = sentence.split("\\s");
		StringBuilder res = new StringBuilder();
		for(String word : words){
			res.append(searchRoot(word, trie)).append(" ");
		}
		return res.substring(0, res.length() - 1);
	}

	private void insert(String word, TrieNode root){
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			char key = word.charAt(i);
			if(!cur.containsKey(key)){
				cur.put(key, new TrieNode());
			}
			cur = cur.get(key);
		}
		cur.setEnd();
	}

	private String searchRoot(String word, TrieNode root){
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			char key = word.charAt(i);
			if(!cur.containsKey(key)){
				return word;
			}
			cur = cur.get(key);
			if(cur.isEnd()){
				return word.substring(0, i + 1);
			}
		}
		return word;
	}
}
