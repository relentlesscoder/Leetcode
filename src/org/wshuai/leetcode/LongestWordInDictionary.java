package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 04/10/2020.
 * #0720 https://leetcode.com/problems/longest-word-in-dictionary/
 */
public class LongestWordInDictionary {

	// time O(n*d), space O(n)
	public String longestWord(String[] words) {
		Arrays.sort(words, (a, b) -> a.length() == b.length() ?
			a.compareTo(b) : b.length() - a.length());
		Set<String> set = new HashSet<>();
		for(String w : words){
			set.add(w);
		}
		for(int i = 0; i < words.length; i++){
			String cur = words[i];
			int j = 1;
			while(j <= cur.length() && set.contains(cur.substring(0, j))){
				j++;
			}
			if(j == cur.length() + 1){
				return cur;
			}
		}
		return "";
	}

	// time O(n*d), space O(n*d)
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
				if (!prev.isEnd() || !prev.containsKey(chars[i])) {
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
