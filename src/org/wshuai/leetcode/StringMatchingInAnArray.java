package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/14/2017.
 * #1408 https://leetcode.com/problems/string-matching-in-an-array/
 */
public class StringMatchingInAnArray {
	// time O(n*d^2), space O(26^d)
	public List<String> stringMatching(String[] words) {
		List<String> res = new ArrayList<>();
		Arrays.sort(words, (a, b) -> b.length() - a.length());
		TrieNode root = new TrieNode();
		for(int i = 0; i < words.length; i++){
			if(search(words[i], root)){
				res.add(words[i]);
			}
			insertWord(words[i], root);
		}
		return res;
	}

	private boolean search(String word, TrieNode root){
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				return false;
			}
			cur = cur.get(c);
		}
		return true;
	}

	private void insertWord(String word, TrieNode root){
		for(int i = 1; i < word.length(); i++){
			insert(word.substring(i), root);
			insert(word.substring(0, i), root);
		}
	}

	private void insert(String word, TrieNode root){
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				cur.put(c, new TrieNode());
			}
			cur = cur.get(c);
		}
	}

	private class TrieNode{
		private static final int R = 26;
		private TrieNode[] links;
		private boolean isEnd;

		public TrieNode(){
			links = new TrieNode[R];
		}

		public boolean containsKey(char key){
			return links[key - 'a'] != null;
		}

		public TrieNode get(char key){
			return links[key - 'a'];
		}

		public void put(char key, TrieNode node){
			links[key - 'a'] = node;
		}

		public boolean isEnd(){
			return isEnd;
		}

		public void setEnd(){
			isEnd = true;
		}
	}
}
