package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/25/2019.
 * #1268 https://leetcode.com/problems/search-suggestions-system/
 */
public class SearchSuggestionsSystem {

	// time O(n*wl), space O(n*wl)
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> res = new ArrayList<>();
		TrieNode root = new TrieNode();
		Arrays.sort(products);
		for(String prod : products){
			insert(prod, root);
		}
		searchPrefix(0, searchWord, root, res);
		while(res.size() < searchWord.length()){
			res.add(new ArrayList<>());
		}
		return res;
	}

	private void searchPrefix(int i, String word, TrieNode node, List<List<String>> res){
		if(i == word.length() || !node.containsKey(word.charAt(i))){
			return;
		}
		node = node.get(word.charAt(i));
		res.add(node.getWords());
		searchPrefix(i + 1, word, node, res);
	}

	private void insert(String word, TrieNode root){
		TrieNode node = root;
		for(char c : word.toCharArray()){
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			node = node.get(c);
			node.addWord(word);
		}
	}

	private class TrieNode{

		private static final int R = 26;

		private TrieNode[] links;

		private List<String> top;

		private TrieNode(){
			links = new TrieNode[R];
			top = new ArrayList<>();
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

		private void addWord(String word){
			if(top.size() == 3){
				return;
			}
			top.add(word);
		}

		private List<String> getWords(){
			return top;
		}
	}
}
