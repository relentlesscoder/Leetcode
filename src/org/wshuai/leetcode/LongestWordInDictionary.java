package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordInDictionary {

	public String longestWord(String[] words) {
		Set<String> set = new HashSet<>();
		int max = 0;
		String res = "";
		Arrays.sort(words, (x, y) -> x.length() == y.length() ? x.compareTo(y) : x.length() - y.length());
		for (String word : words) {
			if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
				set.add(word);
				if (word.length() > max) {
					max = word.length();
					res = word;
				}
			}
		}

		return res;
	}

	public String longestWordTrie(String[] words) {
		int max = 0;
		String res = "";
		Arrays.sort(words, (x, y) -> x.length() == y.length() ? x.compareTo(y) : x.length() - y.length());
		TrieNode root = new TrieNode();
		for (String word : words) {
			char[] chars = word.toCharArray();
			TrieNode prev = root;
			boolean buildable = true;
			for (int i = 0; i < chars.length - 1; i++) {
				if (!prev.isEnd() || prev.containsKey(chars[i])) {
					buildable = false;
					break;
				}
				prev = prev.get(chars[i]);
			}
			if (!buildable) {
				continue;
			}
			prev.setEnd();
			prev.put(chars[chars.length - 1], new TrieNode());
			if (chars.length > max) {
				max = chars.length;
				res = word;
			}
		}
		return res;
	}
}
