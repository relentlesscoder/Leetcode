package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/18/19.
 * #1032 https://leetcode.com/problems/stream-of-characters/
 */
public class StreamOfCharacters {
	private TrieNode root;
	private TrieNode curr;
	private List<TrieNode> query;

	public StreamOfCharacters(String[] words) {
		root = new TrieNode();
		curr = root;
		for(String word : words){
			insert(word);
		}
		query = new ArrayList<>();
	}

	public boolean query(char letter) {
		boolean res = false;
		List<TrieNode> curr = new ArrayList<>();
		TrieNode node = search(root, letter);
		if(node != null && node.isEnd()){
			curr.add(node);
			res = true;
		}else if(node != null){
			curr.add(node);
		}
		for(TrieNode prev : query){
			node = search(prev, letter);
			if(node != null && node.isEnd()){
				curr.add(node);
				res = true;
			}else if(node != null){
				curr.add(node);
			}
		}
		query = curr;
		return res;
	}

	private TrieNode search(TrieNode node, char c){
		if(!node.containsKey(c)){
			return null;
		}
		return node.get(c);
	}

	private void insert(String word){
		TrieNode curr = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!curr.containsKey(c)){
				curr.put(c, new TrieNode());
			}
			curr = curr.get(c);
		}
		curr.setEnd();
	}
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
