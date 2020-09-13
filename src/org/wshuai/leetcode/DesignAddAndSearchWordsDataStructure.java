package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0211 https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class DesignAddAndSearchWordsDataStructure {

	private TrieNode root;

	/** Initialize your data structure here. */
	public DesignAddAndSearchWordsDataStructure() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			node = node.get(c);
		}
		node.setEnd();
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		return dfs(root, word, 0);
	}

	private boolean dfs(TrieNode node, String word, int i){
		if(i == word.length()){
			return node != null && node.isEnd();
		}
		if(node == null){
			return false;
		}
		char k = word.charAt(i);
		if(k != '.'){
			return dfs(node.get(k), word, i + 1);
		}else{
			for(char c = 'a'; c <= 'z'; c++){
				if(dfs(node.get(c), word, i + 1)){
					return true;
				}
			}
		}
		return false;
	}

	private class TrieNode{

		private static final int R = 26;

		private TrieNode[] links;

		private boolean isEnd;

		private TrieNode(){
			links = new TrieNode[R];
		}

		private boolean containsKey(char key){
			return links[key - 'a'] != null;
		}

		private TrieNode get(char key){
			return links[key - 'a'];
		}

		private void put(char key, TrieNode node){
			links[key - 'a'] = node;
		}

		private boolean isEnd(){
			return isEnd;
		}

		private void setEnd(){
			isEnd = true;
		}
	}
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
