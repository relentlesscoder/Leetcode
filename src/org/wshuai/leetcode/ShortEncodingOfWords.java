package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/31/2019.
 * #0820 https://leetcode.com/problems/short-encoding-of-words/
 */
public class ShortEncodingOfWords {

	// time O(n*d), space O(26^d)
	public int minimumLengthEncoding(String[] words) {
		int res = 0;
		TrieNode root = new TrieNode();
		Arrays.sort(words, (a, b) -> b.length() - a.length());
		for(String word : words){
			if(searchPostfix(word, root)){
				continue;
			}
			insertReverse(word, root);
			res += word.length() + 1;
		}
		return res;
	}

	private void insertReverse(String word, TrieNode root){
		TrieNode cur = root;
		for(int i = word.length() - 1; i >= 0; i--){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				cur.put(c, new TrieNode());
			}
			cur = cur.get(c);
		}
		cur.setEnd();
	}

	private boolean searchPostfix(String word, TrieNode root){
		TrieNode cur = root;
		for(int i = word.length() - 1; i >= 0; i--){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				return false;
			}
			cur = cur.get(c);
		}
		return true;
	}

	private class TrieNode{

		private static final int R = 26;

		private TrieNode[] links;

		private boolean end;

		public TrieNode(){
			links = new TrieNode[R];
			end = false;
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
			return end;
		}

		public void setEnd(){
			end = true;
		}
	}
}
