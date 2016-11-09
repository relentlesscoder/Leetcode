package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/16.
 * #211 https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class AddAndSearchWordDataStructureDesign {
  private TrieWD trie = new TrieWD();

  // Adds a word into the data structure.
  public void addWord(String word) {
    trie.add(word);
  }

  // Returns if the word is in the data structure. A word could
  // contain the dot character '.' to represent any one letter.
  public boolean search(String word) {
    return trie.search(word);
  }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

class TrieWD{
  private TrieNode root;

  public TrieWD(){
    root = new TrieNode();
  }

  public void add(String s){
    if(s == null || s.isEmpty()){
      return;
    }
    int i = 0;
    int len = s.length();
    TrieNode curr = root;
    while(i < len){
      char c = s.charAt(i);
      int j = c - 'a';
      TrieNode nxt = curr.nodes[j];
      if(nxt == null){
        nxt = new TrieNode();
        curr.nodes[j] = nxt;
      }
      curr = nxt;
      i++;
    }
    curr.valid = true;
  }

  public boolean search(String s){
    return searchUtil(s, root, 0);
  }

  private boolean searchUtil(String s, TrieNode root, int i){
    if(root == null){
      return false;
    }
    if(i == s.length()){
      return root.valid;
    }
    char val = s.charAt(i);
    if(val == '.'){
      for(TrieNode n : root.nodes){
        if(searchUtil(s, n, i+1)){
          return true;
        }
      }
    }else{
      int p = val - 'a';
      return searchUtil(s, root.nodes[p], i+1);
    }

    return false;
  }
}
