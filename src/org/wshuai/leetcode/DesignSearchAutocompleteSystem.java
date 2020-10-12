package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/09/2019.
 * #0642 https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class DesignSearchAutocompleteSystem {

	private TrieNode root, cur;

	private StringBuilder in;

	public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
		in = new StringBuilder();
		root = new TrieNode();
		cur = root;
		for(int i = 0; i < sentences.length; i++){
			insert(sentences[i], times[i]);
		}
	}

	public List<String> input(char c) {
		List<String> res = new ArrayList<>();

		// hit the end of the input
		if(c == '#'){
			// add one hit to the trie
			insert(in.toString(), 1);
			// reset status
			in = new StringBuilder();
			cur = root;
			return res;
		}

		in.append(c);
		// prefix not found in trie
		if(cur == null || !cur.containsKey(c)){
			cur = null;
			return res;
		}
		cur = cur.get(c);

		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b)
			-> a.getValue().equals(b.getValue()) ?
			a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
		for(Map.Entry<String, Integer> entry : cur.getWordCounts()){
			pq.offer(entry);
		}
		int count = 0;
		while(count++ < 3 && !pq.isEmpty()){
			res.add(pq.poll().getKey());
		}
		return res;
	}

	private void insert(String sentence, int count){
		TrieNode node = root;
		for(int i = 0; i < sentence.length(); i++){
			char c = sentence.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			node = node.get(c);
			node.addWordCount(sentence, count);
		}
	}

	private class TrieNode{

		private static final int R = 27;

		private TrieNode[] links;

		private boolean isEnd;

		private Map<String, Integer> map;

		private TrieNode(){
			links = new TrieNode[R];
			map = new HashMap<>();
			isEnd = false;
		}

		private TrieNode get(char key){
			return key == ' ' ? links[26] : links[key - 'a'];
		}

		private void put(char key, TrieNode node){
			if(key == ' '){
				links[26] = node;
				return;
			}
			links[key - 'a'] = node;
		}

		private boolean containsKey(char key){
			return (key == ' ' ? links[26] : links[key - 'a']) != null;
		}

		private void addWordCount(String word, int count){
			map.put(word, map.getOrDefault(word, 0) + count);
		}

		private Set<Map.Entry<String, Integer>> getWordCounts(){
			return map.entrySet();
		}
	}
}
