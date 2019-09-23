package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/16.
 * #211 https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class AddAndSearchWordDataStructureDesign {
	private ImplementTrie trie = new ImplementTrie();

	// Adds a word into the data structure.
	public void addWord(String word) {
		trie.insert(word);
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return trie.search(word);
	}
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
