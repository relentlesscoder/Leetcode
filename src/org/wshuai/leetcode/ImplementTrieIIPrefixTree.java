package org.wshuai.leetcode;

/**
 * Created by Wei on 12/27/2023.
 * #1804 https://leetcode.com/problems/implement-trie-ii-prefix-tree/
 */
public class ImplementTrieIIPrefixTree {

    // time O(n), space O(n)
    private class Trie {

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = this.root;
            for (char c : word.toCharArray()) {
                if (!curr.containsKey(c)) {
                    curr.put(c, new TrieNode());
                }
                curr = curr.get(c);
                curr.incre();
            }
            curr.increWordCount();
        }

        public int countWordsEqualTo(String word) {
            TrieNode curr = this.root;
            for (char c : word.toCharArray()) {
                if (!curr.containsKey(c)) {
                    return 0;
                }
                curr = curr.get(c);
            }
            return curr.getWordCount();
        }

        public int countWordsStartingWith(String prefix) {
            TrieNode curr = this.root;
            for (char c : prefix.toCharArray()) {
                if (!curr.containsKey(c)) {
                    return 0;
                }
                curr = curr.get(c);
            }
            return curr.getCount();
        }

        public void erase(String word) {
            TrieNode curr = this.root;
            for (char c : word.toCharArray()) {
                if (!curr.containsKey(c)) {
                    curr.put(c, new TrieNode());
                }
                curr = curr.get(c);
                curr.decre();
            }
            curr.decreWordCount();
        }

        private class TrieNode {

            private TrieNode[] nodes;

            private int count;

            private int wordCount;

            private boolean isEnd;

            private TrieNode() {
                this.nodes = new TrieNode[26];
                this.count = 0;
                this.wordCount = 0;
            }

            private boolean containsKey(char key) {
                return nodes[key - 'a'] != null;
            }

            private TrieNode get(char key) {
                return nodes[key - 'a'];
            }

            private void put(char key, TrieNode node) {
                nodes[key - 'a'] = node;
            }

            private void incre() {
                this.count++;
            }

            private void decre() {
                this.count--;
            }

            private int getCount() {
                return this.count;
            }

            private void increWordCount() {
                this.wordCount++;
            }

            private void decreWordCount() {
                this.wordCount--;
            }

            private int getWordCount() {
                return this.wordCount;
            }
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
}
