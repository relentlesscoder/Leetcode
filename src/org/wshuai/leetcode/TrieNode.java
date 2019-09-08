package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/16.
 * Updated by Wei on 9/8/19.
 */
public class TrieNode {

  // R links to node children
  private TrieNode[] links;

  private final int R = 26;

  private boolean isEnd;

  public TrieNode(){
    this.links = new TrieNode[R];
  }

  public boolean containsKey(char key){
    return links[key - 'a'] != null;
  }

  public TrieNode get(char key){
    return links[key-'a'];
  }

  public void put(char key, TrieNode node){
    links[key-'a'] = node;
  }

  public void setEnd(){
    isEnd = true;
  }

  public boolean isEnd(){
    return isEnd;
  }
}
