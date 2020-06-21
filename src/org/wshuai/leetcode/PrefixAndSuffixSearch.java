package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2019.
 * #0745 https://leetcode.com/problems/prefix-and-suffix-search/
 */
public class PrefixAndSuffixSearch {

	private TrieNode root;

	public PrefixAndSuffixSearch(String[] words) {
		root = new TrieNode();
		for(int i = 0; i < words.length; i++){
			String word = words[i];
			insert("{" + word, i);
			for(int j = word.length() - 1; j >= 0; j--){
				insert(word.substring(j) + "{" + word, i);
			}
		}
	}

	public int f(String prefix, String suffix) {
		TrieNode node = search(suffix + "{" + prefix);
		return node == null ? -1 : node.getWeight();
	}

	private void insert(String word, int weight){
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			node = node.get(c);
			node.setWeight(weight);
		}
	}

	private TrieNode search(String word){
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				return null;
			}
			node = node.get(c);
		}
		return node;
	}

	private class TrieNode{

		private static final int R = 27;

		private TrieNode[] links;

		private int weight;

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

		public int getWeight(){
			return this.weight;
		}

		public void setWeight(int weight){
			this.weight = weight;
		}

	}
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
