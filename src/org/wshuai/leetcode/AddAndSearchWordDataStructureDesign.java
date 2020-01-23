package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0211 https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class AddAndSearchWordDataStructureDesign {
	private TrieNode root;

	/** Initialize your data structure here. */
	public AddAndSearchWordDataStructureDesign() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		if(word == null || word.length() == 0){
			return;
		}
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!cur.containsKey(c)){
				cur.put(c, new TrieNode());
			}
			cur = cur.get(c);
		}
		cur.setEnd();
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		if(word == null || word.length() == 0){
			return false;
		}
		TrieNode cur = root;
		return dfs(0, word, cur);
	}

	private boolean dfs(int i, String word, TrieNode cur){
		if(i == word.length()){
			return cur != null && cur.isEnd();
		}
		char c = word.charAt(i);
		if(c != '.' && !cur.containsKey(c)){
			return false;
		}
		if(c != '.'){
			return dfs(i + 1, word, cur.get(c));
		}
		for(char j = 'a'; j <= 'z'; j++){
			if(!cur.containsKey(j)){
				continue;
			}
			if(dfs(i + 1, word, cur.get(j))){
				return true;
			}
		}
		return false;
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

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
