package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #1858 https://leetcode.com/problems/longest-word-with-all-prefixes/
 */
public class LongestWordWithAllPrefixes {

	// time O(n * L), space O(n * L)
	public String longestWord(String[] words) {
		String res = "";
		int length = 0;
		TrieNode root = new TrieNode();
		for (String word : words) {
			insert(word, root);
		}
		for (String word : words) {
			if (searchPrefix(word, root)
					&& (length < word.length() || (length == word.length() && word.compareTo(res) < 0))) { // ensure we get the lexicographically smallest
				length = word.length();
				res = word;
			}
		}
		return res;
	}

	private boolean searchPrefix(String word, TrieNode root) {
		TrieNode curr = root;
		int i = 0;
		for (; i < word.length(); i++) {
			char c = word.charAt(i);
			if (curr.containsKey(c) && curr.get(c).isEnd()) { // every node in the path is an end if all prefix is presented
				curr = curr.get(c);
			} else {
				return false;
			}
		}
		return true;
	}

	private void insert(String word, TrieNode root) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!curr.containsKey(c)) {
				curr.put(c, new TrieNode());
			}
			curr = curr.get(c);
		}
		curr.setEnd();
	}

	private class TrieNode {

		private TrieNode[] nodes;
		private boolean isEnd;

		public TrieNode() {
			nodes = new TrieNode[26];
			isEnd = false;
		}

		private boolean containsKey(char c) {
			return nodes[c - 'a'] != null;
		}

		private TrieNode get(char c) {
			return nodes[c - 'a'];
		}

		private void put(char c, TrieNode node) {
			nodes[c - 'a'] = node;
		}

		private boolean isEnd() {
			return this.isEnd;
		}

		private void setEnd() {
			this.isEnd = true;
		}
	}
}
