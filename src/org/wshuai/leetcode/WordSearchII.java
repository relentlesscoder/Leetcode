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

	private static final int[] DIRECTIONS = new int[]{0, 1, 0, -1, 0};

	// time O(mn4^wl), space O(26*wl)
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> res = new HashSet<>();
		if(board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0){
			return new ArrayList<>();
		}
		int m = board.length, n = board[0].length;
		TrieNode root = new TrieNode();
		for(String w : words){
			insert(w, root);
		}
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				dfs(i, j, board, root, "", res);
			}
		}
		return new ArrayList<>(res);
	}

	private void dfs(int i, int j, char[][] board, TrieNode node, String cur, Set<String> res){
		if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '*' || !node.containsKey(board[i][j])){
			return;
		}
		char val = board[i][j];
		board[i][j] = '*';
		TrieNode next = node.get(val);
		cur += val;
		if(next.isEnd()){
			res.add(cur);
		}
		for(int k = 0; k < 4; k++){
			int x = i + DIRECTIONS[k], y = j + DIRECTIONS[k + 1];
			dfs(x, y, board, next, cur, res);
		}
		board[i][j] = val;
	}

	private void insert(String word, TrieNode root){
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

		private TrieNode(){
			links = new TrieNode[R];
			isEnd = false;
		}

		private TrieNode get(char key){
			return links[key - 'a'];
		}

		private void put(char key, TrieNode node){
			links[key - 'a'] = node;
		}

		private boolean containsKey(char key){
			return links[key - 'a'] != null;
		}

		private boolean isEnd(){
			return isEnd;
		}

		private void setEnd(){
			isEnd = true;
		}
	}
}