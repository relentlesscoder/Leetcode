package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/09/2019.
 * #0642 https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class DesignSearchAutocompleteSystem {

	private static final int LIMIT = 3;

	private TrieNode root, cur;

	private StringBuilder in;

	public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
		this.root = new TrieNode();
		this.cur = root;
		this.in = new StringBuilder();
		for(int i = 0; i < sentences.length; i++){
			insert(sentences[i], times[i]);
		}
	}

	public List<String> input(char c) {
		List<String> res = new ArrayList<>();

		if(c == '#'){ // current input is done
			insert(in.toString(), 1); // increment the current word count by 1
			cur = root; // reset current trie node
			in = new StringBuilder(); // reset input
			return res;
		}

		in.append(c); // capture the input character
		if(cur == null || !cur.containsKey(c)){ // no match is found
			cur = null; // set to null to avoid potential bug
			return res;
		}
		cur = cur.get(c);
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) ->
			a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey())
				: b.getValue() - a.getValue());
		for(Map.Entry<String, Integer> entry : cur.getWordCount()){
			pq.offer(entry);
		}
		for(int i = 0; i < LIMIT && !pq.isEmpty(); i++){
			res.add(pq.poll().getKey());
		}
		return res;
	}

	private void insert(String word, int count){
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			node = node.get(c);
			node.addWordCount(word, count);
		}
	}

	private class TrieNode{

		private TrieNode[] nodes;

		private Map<String, Integer> wordFreq;

		private TrieNode(){
			this.nodes = new TrieNode[27];
			this.wordFreq = new HashMap<>();
		}

		private boolean containsKey(char key){
			return nodes[key == ' ' ? 26 : key - 'a'] != null;
		}

		private void put(char key, TrieNode node){
			nodes[key == ' ' ? 26 : key - 'a'] = node;
		}

		private TrieNode get(char key){
			return nodes[key == ' ' ? 26 : key - 'a'];
		}

		private void addWordCount(String word, int count){
			wordFreq.put(word, wordFreq.getOrDefault(word, 0) + count);
		}

		private Set<Map.Entry<String, Integer>> getWordCount(){
			return wordFreq.entrySet();
		}
	}
}
