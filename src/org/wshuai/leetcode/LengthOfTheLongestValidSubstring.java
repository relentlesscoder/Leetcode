package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 01/22/2024.
 * #2781 https://leetcode.com/problems/length-of-the-longest-valid-substring/
 */
public class LengthOfTheLongestValidSubstring {

    // time O(10 * n + 10 * m), space O(26^10)
    public int longestValidSubstring(String word, List<String> forbidden) {
        int res = 0;
        TrieNode root = new TrieNode();
        for (String f : forbidden) {
            insert(f, root);
        }
        for (int i = 0, j = 0; i < word.length(); i++) {
            int match = search(word, i, j, root); // Search in Trie to find the matching postfix
            if (match != -1) { // Found matching postfix
                j = i - match + 2; // Advance the start of the sliding window to point to the second character of the matching postfix to make the substring valid
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    private void insert(String s, TrieNode root) {
        TrieNode node = root;
        for (int i = s.length() - 1; i >= 0; i--) {
            char key = s.charAt(i);
            if (!node.containsKey(key)) {
                node.put(key, new TrieNode());
            }
            node = node.get(key);
        }
        node.setEnd();
    }

    private int search(String s, int start, int end, TrieNode root)
    {
        int res = 0;
        TrieNode node = root;
        for (int i = start; i >= end; i--) {
            char key = s.charAt(i);
            if (!node.containsKey(key)) {
                return -1;
            }
            node = node.get(key);
            res++;
            if (node.isEnd()) {
                return res;
            }
        }
        return -1;
    }

    private class TrieNode{

        private TrieNode[] nodes;

        private boolean isEnd;

        private TrieNode() {
            this.nodes = new TrieNode[26];
            this.isEnd = false;
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

        private boolean isEnd() {
            return this.isEnd;
        }

        private void setEnd() {
            this.isEnd = true;
        }
    }
}
