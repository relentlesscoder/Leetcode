package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 12/4/16.
 * #126 https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadderII {
  //BFS
  public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    List<List<String>> lst = new ArrayList<List<String>>();
    if(beginWord == null || beginWord.isEmpty() || endWord == null ||
      endWord.isEmpty() || wordList == null || wordList.size() == 0){
      return lst;
    }
    boolean end = false;
    List<List<String>> tmp = new ArrayList<List<String>>();
    List<String> def = new ArrayList<String>();
    def.add(beginWord);
    tmp.add(def);
    wordList.add(endWord);
    while(!end && tmp.size() > 0 && wordList.size() > 0){
      List<List<String>> curr = new ArrayList<List<String>>();
      Set<String> rem = new HashSet<String>();
      for(int k = 0; k < tmp.size(); k++){
        List<String> l = tmp.get(k);
        String tail = l.get(l.size()-1);
        int len = tail.length();
        char[] chars = tail.toCharArray();
        for(int i = 0; i < len; i++){
          char val = chars[i];
          for(char j = 'a'; j <= 'z'; j++){
            if(val == j){
              continue;
            }
            chars[i] = j;
            String str = String.valueOf(chars);
            if(wordList.contains(str)){
              List<String> path = new ArrayList<String>(l);
              path.add(str);
              curr.add(path);
              rem.add(str);
              end = end || str.equals(endWord);
            }
          }
          chars[i] = val;
        }
      }
      for(String str: rem){
        wordList.remove(str);
      }
      tmp = curr;
    }
    if(end){
      for(List<String> ls: tmp){
        String tail = ls.get(ls.size()-1);
        if(tail.equals(endWord)){
          lst.add(ls);
        }
      }
    }
    return lst;
  }
}
