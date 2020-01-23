package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 */
public class TrieNode {

	// R links to node children
  public TrieNode[] links;

  private final int R = 52;

  private boolean isEnd;

  public TrieNode(){
    this.links = new TrieNode[R];
  }

  public boolean containsKey(char key){
    int index = getIndex(key);
    return links[index] != null;
  }

  public TrieNode get(char key){
    int index = getIndex(key);
    return links[index];
  }

  public void put(char key, TrieNode node){
    int index = getIndex(key);
    links[index] = node;
  }

  public boolean isEnd(){
    return isEnd;
  }

  public void setEnd(){
    isEnd = true;
  }

  private int getIndex(char key){
    if(Character.isUpperCase(key)){
      return (key - 'A') + 26;
    }
    return key - 'a';
  }
}
