package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0208 https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class ImplementTrie {

    private static class Trie {

        private final TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        // time O(n), space O(n)
        public void insert(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.containsKey(c)) {
                    curr.put(c, new TrieNode());
                }
                curr = curr.get(c);
            }
            curr.setEnd();
        }

        // time O(n), space O(1)
        public boolean search(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.containsKey(c)) {
                    return false;
                }
                curr = curr.get(c);
            }
            return curr.isEnd();
        }

        // time O(n), space O(1)
        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                if (!curr.containsKey(c)) {
                    return false;
                }
                curr = curr.get(c);
            }
            return true;
        }

        private static class TrieNode {

            private final TrieNode[] nodes;
            private boolean end;

            public TrieNode() {
                nodes = new TrieNode[26];
            }

            public TrieNode get(char c) {
                return nodes[c - 'a'];
            }

            public boolean containsKey(char c) {
                return nodes[c - 'a'] != null;
            }

            public void put(char c, TrieNode node) {
                nodes[c - 'a'] = node;
            }

            public boolean isEnd() {
                return this.end;
            }

            public void setEnd() {
                this.end = true;
            }
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
