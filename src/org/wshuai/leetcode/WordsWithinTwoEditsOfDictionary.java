package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/26/2023.
 * #2452 https://leetcode.com/problems/words-within-two-edits-of-dictionary/
 */
public class WordsWithinTwoEditsOfDictionary {

	// time O(n * k + m * 26^k), space O(n * k * 26)
	public List<String> twoEditWords(String[] queries, String[] dictionary) {
		List<String> res = new ArrayList<>();
		TrieNode root = new TrieNode();
		for (String word : dictionary) {
			insert(word, root);
		}
		for (String query : queries) {
			TrieNode curr = root;
			if (search(0, 0, query, curr)) {
				res.add(query);
			}
		}
		return res;
	}

	private boolean search(int i, int diff, String s, TrieNode node) {
		if (i == s.length()) {
			return diff <= 2;
		}
		if (diff > 2) {
			return false;
		}
		boolean valid = false;
		for (char c = 'a';  c <= 'z'; c++) {
			if (!node.containsKey(c)) {
				continue;
			}
			valid |= search(i + 1, diff + (c == s.charAt(i) ? 0 : 1), s, node.get(c));
		}
		return valid;
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
	}

	private class TrieNode {
		private TrieNode[] links;

		public TrieNode() {
			links = new TrieNode[26];
		}

		public boolean containsKey(char key) {
			return links[key - 'a'] != null;
		}

		public TrieNode get(char key) {
			return links[key - 'a'];
		}

		public void put(char key, TrieNode node) {
			links[key - 'a'] = node;
		}
	}
}
