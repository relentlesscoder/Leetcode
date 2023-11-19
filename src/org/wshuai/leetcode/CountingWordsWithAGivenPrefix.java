package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2185 https://leetcode.com/problems/counting-words-with-a-given-prefix/
 */
public class CountingWordsWithAGivenPrefix {

	// time O(n * m), space O(m)
	public int prefixCount(String[] words, String pref) {
		int res = 0;
		TrieNode root = new TrieNode();
		insert(pref, root);
		for (String word : words) {
			if (containsPrefix(word, root)) {
				res++;
			}
		}
		return res;
	}

	private void insert(String pref, TrieNode root) {
		TrieNode node = root;
		for (char c : pref.toCharArray()) {
			if (!node.containsKey(c)) {
				node.put(c, new TrieNode());
			}
			node = node.get(c);
		}
		node.setEnd(true);
	}

	private boolean containsPrefix(String word, TrieNode root) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!node.containsKey(c)) {
				break;
			}
			node = node.get(c);
		}
		return node.isEnd();
	}

	private class TrieNode{

		private static final int N = 26;
		private TrieNode[] nodes;
		private boolean isEnd;

		public TrieNode() {
			nodes = new TrieNode[N];
			isEnd = false;
		}

		public boolean containsKey(char key) {
			return nodes[key - 'a'] != null;
		}

		public TrieNode get(char key) {
			return nodes[key - 'a'];
		}

		public void put(char key, TrieNode node) {
			nodes[key - 'a'] = node;
		}

		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}

		public boolean isEnd() {
			return this.isEnd;
		}
	}
}
