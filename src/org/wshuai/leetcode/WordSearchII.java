package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 02/14/2017.
 * #0212 https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {
	private static int[][] dirs = new int[][]{
		{1, -1, 0, 0},
		{0, 0, 1, -1}
	};
	private TrieNode root;

	// time O(mn4^wl), space O(26*wl)
	// dfs + trie
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> res = new HashSet<>();
		if(board == null || board.length == 0 || board[0].length == 0){
			return new ArrayList<>();
		}
		int r = board.length, c = board[0].length;
		root = new TrieNode();
		for(String w : words){
			addWord(w);
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				dfs(i, j, r, c, "", board, root, res);
			}
		}
		return new ArrayList<>(res);
	}

	private void dfs(int i, int j, int r, int c, String cur, char[][] board, TrieNode trie, Set<String> res){
		if(i < 0 || i >= r || j < 0 || j >= c || board[i][j] == '*' || !trie.containsKey(board[i][j])){
			return;
		}
		char val = board[i][j];
		cur += val;
		TrieNode next = trie.get(val);
		if(next.isEnd()){
			res.add(cur);
		}
		board[i][j] = '*';
		for(int k = 0; k < 4; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			dfs(x, y, r, c, cur, board, next, res);
		}
		board[i][j] = val;
	}

	private void addWord(String word) {
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