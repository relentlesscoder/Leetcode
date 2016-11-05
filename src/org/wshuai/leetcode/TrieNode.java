package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/16.
 */
public class TrieNode {
  TrieNode[] nodes;
  boolean valid;

  public TrieNode(){
    this.nodes = new TrieNode[26];
    this.valid = false;
  }
}
