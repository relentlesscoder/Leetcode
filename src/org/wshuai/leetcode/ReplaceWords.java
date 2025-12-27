package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/08/2019.
 * #0648 https://leetcode.com/problems/replace-words/
 */
public class ReplaceWords {

    // time O(n + m), space O(26 * n)
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder res = new StringBuilder();
        TrieNode root = new TrieNode();
        for (String word : dictionary) { // O(m)
            insert(word, root);
        }
        String[] words = sentence.split(" ");
        for (String word : words) { // O(n) where n is length of the sentence
            res.append(replace(word, root)).append(" ");
        }
        return res.substring(0, res.length() - 1);
    }

    private String replace(String word, TrieNode root) {
        TrieNode curr = root;
        int i = 0;
        for (; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.containsKey(c)) {
                return word;
            }
            curr = curr.get(c);
            if (curr.isEnd()) {
                break;
            }
        }
        return curr.isEnd() ? word.substring(0, i + 1) : word;
    }

    private void insert(String word, TrieNode root) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.setEnd();
    }

    private static class TrieNode {

        private static final int N = 26;
        private TrieNode[] nodes;
        private boolean end;

        public TrieNode() {
            nodes = new TrieNode[N];
            end = false;
        }

        public boolean containsKey(char c) {
            return nodes[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return nodes[c - 'a'];
        }

        public void put(char c, TrieNode node) {
            nodes[c - 'a'] = node;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd() {
            end = true;
        }
    }
}
