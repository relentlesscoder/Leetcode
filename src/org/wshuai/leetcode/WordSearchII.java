package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 2/14/17.
 * #212 https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {
  //DFS & Trie
  public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<String>();
    if(words == null || words.length == 0){
      return res;
    }
    if(board == null || board.length == 0 || board[0].length == 0){
      return res;
    }
    ImplementTrie trie = new ImplementTrie();
    int len = words.length;
    for(int i = 0; i < len; i++){
      trie.insert(words[i]);
    }
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] used = new boolean[rows][cols];
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        findWordsUtil(i, j, sb, used, board, trie, res, rows, cols);
      }
    }
    return res;
  }

  private void findWordsUtil(int i, int j, StringBuilder sb, boolean[][] used, char[][] board,
                             ImplementTrie trie, List<String> res, int rows, int cols){
    if(i < 0 || j < 0 || i >= rows || j >= cols){
      return;
    }
    if(used[i][j]){
      return;
    }
    used[i][j] = true;
    sb.append(board[i][j]);
    String str = sb.toString();
    TrieNode node = trie.searchNode(str);
    if(node != null){
      if(node.valid && !res.contains(str)){
        res.add(str);
      }
      findWordsUtil(i-1, j, sb, used, board, trie, res, rows, cols);
      findWordsUtil(i+1, j, sb, used, board, trie, res, rows, cols);
      findWordsUtil(i, j-1, sb, used, board, trie, res, rows, cols);
      findWordsUtil(i, j+1, sb, used, board, trie, res, rows, cols);
    }
    sb.deleteCharAt(sb.length()-1);
    used[i][j] = false;
  }
}