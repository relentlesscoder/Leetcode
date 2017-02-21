package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #208 https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class ImplementTrie {
  private TrieNode root;

  public ImplementTrie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    if (word == null || word.isEmpty())
    {
      return;
    }
    int len = word.length();
    int i = 0;
    TrieNode curr = root;
    while (i < len)
    {
      char c = word.charAt(i);
      int idx = c - 'a';
      TrieNode next = curr.nodes[idx];
      if (next == null)
      {
        next = new TrieNode();
        curr.nodes[idx] = next;
      }
      curr = next;
      i++;
    }
    curr.valid = true;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    TrieNode node = searchNode(word);
    if (node == null)
    {
      return false;
    }
    return node.valid;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    TrieNode node = searchNode(prefix);
    if (node == null)
    {
      return false;
    }
    return true;
  }

  public TrieNode searchNode(String word){
    int len = word.length();
    int i = 0;
    TrieNode curr = root;
    while (i < len)
    {
      char c = word.charAt(i);
      int idx = c - 'a';
      TrieNode next = curr.nodes[idx];
      if (next == null)
      {
        return null;
      }
      else
      {
        curr = next;
      }
      i++;
    }
    if (curr == root)
    {
      return null;
    }
    return curr;
  }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
