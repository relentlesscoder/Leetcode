package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/05/2019.
 * #0336 https://leetcode.com/problems/palindrome-pairs/
 */
public class PalindromePairs {
	// time O(n*k^2), space O(k) k is the average length of the words
	// https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<>();
		TrieNode root = new TrieNode();

		for(int i = 0; i < words.length; i++){
			add(root, words[i], i);
		}

		for(int i = 0; i < words.length; i++){
			search(words[i], i, root, res);
		}
		return res;
	}

	private void add(TrieNode node, String word, int index){
		for(int i = word.length() - 1; i >= 0; i--){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			if(isPalindrome(word, 0, i)){
				node.list.add(index);
			}
			node = node.get(c);
		}
		node.list.add(index);
		node.index = index;
	}

	private void search(String cur, int i, TrieNode node, List<List<Integer>> res){
		for(int j = 0; j < cur.length(); j++){
			// if the current string (batccc) is longer than a reverse word (bat) in the trie
			// after the search we still need to check if ccc is palindrome
			if(node.index >= 0 && node.index != i && isPalindrome(cur, j, cur.length() - 1)){
				res.add(Arrays.asList(i, node.index));
			}

			node = node.get(cur.charAt(j));
			if(node == null){
				return;
			}
		}

		// if the entire word (bat) is being matched, we need to check if there is a reverse word
		// in trie that satisfy the two conditions below:
		// 1. fully matches the word (bat)
		// 2. the rest of the word is a palindrome (batccc)
		for(int j : node.list){
			if(i == j){
				continue;
			}
			res.add(Arrays.asList(i, j));
		}
	}

	// time O(n*k^2), space O(n)
	public List<List<Integer>> palindromePairsHashMap(String[] words) {
		if (words == null || words.length < 2) {
			return null;
		}
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			String rev = new StringBuilder(words[i]).reverse().toString();
			map.put(rev, i);
		}
		for (int i = 0; i < words.length; i++) {
			int len = words[i].length();
			for (int j = 0; j <= len; j++) {
				String left = words[i].substring(0, j);
				String right = j == len ? "" : words[i].substring(j);
				if (isPalindrome(left, 0, left.length() - 1) && map.containsKey(right) && map.get(right) != i) {
					res.add(Arrays.asList(map.get(right), i));
				}
				if (isPalindrome(right, 0, right.length() - 1) && map.containsKey(left) && map.get(left) != i
						//avoid duplicates
						&& right.length() != 0) {
					res.add(Arrays.asList(map.get(left), i));
				}
			}
		}
		return res;
	}

	private boolean isPalindrome(String s, int i, int j){
		while(i < j){
			if(s.charAt(i++) != s.charAt(j--)){
				return false;
			}
		}
		return true;
	}

	private class TrieNode{

		TrieNode[] links;
		int index;
		List<Integer> list;

		public TrieNode(){
			links = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
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
	}
}
