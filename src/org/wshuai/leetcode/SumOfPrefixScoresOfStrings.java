package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2023.
 * #2416 https://leetcode.com/problems/sum-of-prefix-scores-of-strings/
 */
public class SumOfPrefixScoresOfStrings {

    // time O(n * l), space O(n * l)
    public int[] sumPrefixScores(String[] words) {
        int[] res = new int[words.length];
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        for (int i = 0; i < words.length; i++) {
            res[i] = searchPrefix(root, words[i]);
        }
        return res;
    }

    private int searchPrefix(TrieNode root, String word) {
        int res = 0;
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.get(c);
            res += curr.getCount();
        }
        return res;
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
            curr.incre();
        }
    }

    private class TrieNode {

        private TrieNode[] nodes;
        private int count;

        private TrieNode() {
            this.count = 0;
            this.nodes = new TrieNode[26];
        }

        private boolean containsKey(char key) {
            return nodes[key - 'a'] != null;
        }

        private void put(char key, TrieNode node) {
            nodes[key - 'a'] = node;
        }

        private TrieNode get(char key) {
            return nodes[key - 'a'];
        }

        private int getCount() {
            return this.count;
        }

        private void incre() {
            this.count++;
        }
    }
}
