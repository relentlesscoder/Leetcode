package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 9/8/2019.
 * #648 https://leetcode.com/problems/replace-words/
 */
public class ReplaceWords {
  public String replaceWords(List<String> dict, String sentence) {
    ImplementTrie trie = new ImplementTrie();
    for(String word: dict){
      trie.insert(word);
    }
    String[] arr = sentence.split(" ");
    StringBuilder sb = new StringBuilder();
    for(String word: arr){
      sb.append(trie.searchRoot(word) + " ");
    }
    String res = sb.toString();
    return res.substring(0, res.length()-1);
  }
}
