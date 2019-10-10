package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/1/19.
 * #1023 https://leetcode.com/problems/camelcase-matching/
 */
public class CamelcaseMatching {
  private TrieNode root;

  public List<Boolean> camelMatch(String[] queries, String pattern) {
    root = new TrieNode();
    insert(pattern);
    List<Boolean> res = new ArrayList<>();
    for(String q: queries){
      res.add(match(q));
    }
    return res;
  }

  private void insert(String word){
    TrieNode node = root;
    for(int i = 0; i < word.length(); i++){
      char key = word.charAt(i);
      if(!node.containsKey(key)){
        node.put(key, new TrieNode());
      }
      node = node.get(key);
    }
    node.setEnd();
  }

  private boolean match(String query){
    TrieNode node = root;
    for(int i = 0; i < query.length(); i++){
      char key = query.charAt(i);
      if(!node.containsKey(key) && Character.isUpperCase(key)){
        return false;
      }
      if(node.containsKey(key)){
        node = node.get(key);
      }
    }
    return node.isEnd();
  }
}
