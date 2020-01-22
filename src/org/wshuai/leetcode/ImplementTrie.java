package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0208 https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class ImplementTrie {
	private TrieNode root;

	public ImplementTrie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentKey = word.charAt(i);
			if (!node.containsKey(currentKey)) {
				node.put(currentKey, new TrieNode());
			}
			node = node.get(currentKey);
		}
		node.setEnd();
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode node = searchPrefix(word);
		return node != null && node.isEnd();
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode node = searchPrefix(prefix);
		return node != null;
	}

	// search a prefix or whole key in trie and
	// returns the node where search ends
	public TrieNode searchPrefix(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentLetter = word.charAt(i);
			if (node.containsKey(currentLetter)) {
				node = node.get(currentLetter);
			} else {
				return null;
			}
		}
		return node;
	}

	// search for the shortest prefix - #648
	public String searchRoot(String successor) {
		TrieNode node = root;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < successor.length(); i++) {
			char key = successor.charAt(i);
			if (node.containsKey(key)) {
				sb.append("" + key);
				node = node.get(key);
			} else {
				return successor;
			}
			if (node.isEnd()) {
				break;
			}
		}
		return sb.toString();
	}
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
