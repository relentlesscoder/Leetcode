package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Wei on 10/17/16.
 * #127 https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
  public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    if(beginWord == null || beginWord.isEmpty() || endWord == null
        || endWord.isEmpty() || wordList == null || wordList.size() == 0){
      return 0;
    }
    wordList.remove(beginWord);

    int depth = 0;
    int len = beginWord.length();
    Queue<String> q = new LinkedList<String>();
    q.offer(beginWord);
    wordList.add(endWord);
    int currLen = 1;
    int nextLen = 0;
    while(!q.isEmpty()){
      String word = q.poll();
      currLen--;
      if(word.equals(endWord)){
        return depth + 1;
      }
      char[] chrs = word.toCharArray();
      for(int i = 0; i < len; i++){
        char oChar = chrs[i];
        for(char j = 'a'; j <= 'z'; j++){
          if(chrs[i] == j){
            continue;
          }
          chrs[i] = j;
          String nWord = String.valueOf(chrs);
          if(wordList.contains(nWord)){
            q.offer(nWord);
            nextLen++;
            wordList.remove(nWord);
          }
          chrs[i] = oChar;
        }
      }

      if(currLen == 0){
        depth++;
        currLen = nextLen;
        nextLen = 0;
      }
    }

    return 0;
  }
}
