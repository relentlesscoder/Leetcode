package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/18/19.
 * #472 https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {
	private TrieNode root;

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		root = new TrieNode();
		List<String> res = new ArrayList<>();
		Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
		for(String w : words){
			if(search(w, 0, root)){
				res.add(w);
			}else{
				insert(w);
			}
		}
		return res;
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

	private boolean search(String word, int j, TrieNode curr){
		boolean res = true;
		for(int i = j; i < word.length(); i++){
			char c = word.charAt(i);
			if(!curr.containsKey(c)){
				return false;
			}
			curr = curr.get(c);
			if(curr.isEnd() && i != word.length() - 1){
				if(search(word, i + 1, curr) || search(word, i + 1, root)){
					return true;
				}
			}
		}
		return curr.isEnd();
	}
}
