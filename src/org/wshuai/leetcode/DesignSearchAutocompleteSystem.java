package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/09/2019.
 * #0642 https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class DesignSearchAutocompleteSystem {

	private TrieNode trie;

	private String in;

	public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
		in = "";
		trie = new TrieNode();
		for(int i = 0; i < sentences.length; i++){
			insert(sentences[i], times[i]);
		}
	}

	public List<String> input(char c) {
		List<String> res = new ArrayList<>();

		// if the current input is '#', increment the current sentence by 1
		// and reset it to empty.
		if(c == '#'){
			insert(in, 1);
			in = "";
			return res;
		}

		in += c;

		TrieNode node = trie;
		for(int i = 0; i < in.length(); i++){
			char cur = in.charAt(i);
			if(!node.containsKey(cur)){
				return res;
			}
			node = node.get(cur);
		}

		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) ->
				a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
		for(Map.Entry<String, Integer> entry : node.getWordCount()){
			pq.offer(entry);
		}
		int count = 0;
		while(count++ < 3 && !pq.isEmpty()){
			res.add(pq.poll().getKey());
		}
		return res;
	}

	private void insert(String word, int count){
		TrieNode node = trie;
		for(int i = 0; i < word.length(); i++){
			char cur = word.charAt(i);
			if(!node.containsKey(cur)){
				node.put(cur, new TrieNode());
			}
			node = node.get(cur);
			// each trie node record the word (with the current prefix) and it's count
			node.addWordCount(word, count);
		}
	}

	private class TrieNode{

		private final int R = 27;

		private TrieNode[] links;

		private Map<String, Integer> map;

		public TrieNode(){
			links = new TrieNode[R];
			map = new HashMap<>();
		}

		public boolean containsKey(char key){
			return (key == ' ' ? links[26] : links[key - 'a']) != null;
		}

		public TrieNode get(char key){
			return key == ' ' ? links[26] : links[key - 'a'];
		}

		public void put(char key, TrieNode node){
			int index = key == ' ' ? 26 : key - 'a';
			links[index] = node;
		}

		public void addWordCount(String word, int count){
			map.put(word, map.getOrDefault(word, 0) + count);
		}

		public Set<Map.Entry<String, Integer>> getWordCount(){
			return map.entrySet();
		}
	}
}
